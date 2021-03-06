package xyz.weechang.paddling.core.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import xyz.weechang.paddling.core.error.IError;
import xyz.weechang.paddling.core.error.SysError;
import xyz.weechang.paddling.core.model.dto.R;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.List;
import java.util.Set;

/**
 * 统一异常处理
 *
 * @author weechang
 */
@Slf4j
@ControllerAdvice
public class PaddlingExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public R handleError(Throwable e, HttpServletResponse servletResponse) {
        servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return build(e, servletResponse);
    }

    private R build(Throwable ex, HttpServletResponse servletResponse) {
        IError error;
        String msg = null;
        if (!(ex instanceof AppException)) {
            log.error(ex.getMessage());
        }
        if (ex instanceof AppException) {
            error = ((AppException) ex).getError();
            msg = error.getMsg();
        } else if (ex instanceof BindException) {
            error = SysError.INVALID_PARAMETER;
            List<ObjectError> errors = ((BindException) ex).getAllErrors();
            if (errors != null && errors.size() != 0) {
                StringBuilder msgSb = new StringBuilder();
                for (ObjectError objectError : errors) {
                    msgSb.append("Field error in object '" + objectError.getObjectName() + " ");
                    if (objectError instanceof FieldError) {
                        msgSb.append("on field " + ((FieldError) objectError).getField() + " ");
                    }
                    msgSb.append(objectError.getDefaultMessage() + " ");
                }
                msg = msgSb.toString();
            }
        } else if (ex instanceof MissingServletRequestParameterException) {
            error = SysError.INVALID_PARAMETER;
            msg = ex.getMessage();
        } else if (ex instanceof ConstraintViolationException) {
            error = SysError.INVALID_PARAMETER;
            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) ex).getConstraintViolations();
            final StringBuilder msgSb = new StringBuilder();
            for (ConstraintViolation<?> constraintViolation : violations) {
                msgSb.append(constraintViolation.getPropertyPath()).append(":").append(constraintViolation.getMessage() + "\n");
            }
            msg = msgSb.toString();
        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
            error = SysError.CONTENT_TYPE_NOT_SUPPORT;
            msg = ex.getMessage();
        } else if (ex instanceof HttpMessageNotReadableException) {
            error = SysError.INVALID_PARAMETER;
            msg = ex.getMessage();
        } else if (ex instanceof MethodArgumentNotValidException) {
            error = SysError.INVALID_PARAMETER;
            msg = ex.getMessage();
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            error = SysError.METHOD_NOT_SUPPORTED;
            msg = ex.getMessage();
        } else if (ex instanceof UnexpectedTypeException) {
            error = SysError.INVALID_PARAMETER;
            msg = ex.getMessage();
        } else if (ex instanceof NoHandlerFoundException) {
            error = SysError.SERVICE_NOT_FOUND;
            msg = ex.getMessage();
        } else {
            error = SysError.SYSTEM_INTERNAL_ERROR;
            msg = ex.getMessage();
        }
        R response = R.error(error);
        response.setMsg(msg);
        int status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        if (error == SysError.INVALID_PARAMETER) {
            status = HttpServletResponse.SC_BAD_REQUEST;
        } else if (error == SysError.METHOD_NOT_SUPPORTED) {
            status = HttpServletResponse.SC_METHOD_NOT_ALLOWED;
        } else if (error == SysError.SERVICE_NOT_FOUND) {
            status = HttpServletResponse.SC_NOT_FOUND;
        } else if (error == SysError.CONTENT_TYPE_NOT_SUPPORT) {
            status = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
        }
        servletResponse.setStatus(status);
        return response;
    }
}
