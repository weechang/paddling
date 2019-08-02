package xyz.weechang.paddling.core.interceptor;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import xyz.weechang.paddling.core.security.PaddlingSecurityUtil;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * SQL记录审计 拦截器
 *
 * @author zhangwei
 * date 2019/7/31
 * time 16:48
 */
@Intercepts(value = {@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class SQLAuditInterceptor extends AbstractSqlParserHandler implements Interceptor {

    /*** 创建时间 */
    private static final String CREATED_TIME = "createdTime";
    /*** 创建人 */
    private static final String CREATED_BY = "createdBy";
    /*** 更新时间 */
    private static final String MODIFIED_TIME = "modifiedTime";
    /*** 更新人 */
    private static final String MODIFIED_BY = "modifiedBy";
    /*** 逻辑删除 */
    private static final String LOGIC = "logic";


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        // SQL操作命令
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        // 获取新增或修改的对象参数
        Object parameter = invocation.getArgs()[1];
        // 获取对象中所有的私有成员变量（对应表字段）
        Field[] declaredFields = parameter.getClass().getDeclaredFields();
        if (parameter.getClass().getSuperclass() != null) {
            Field[] superField = parameter.getClass().getSuperclass().getDeclaredFields();
            declaredFields = ArrayUtil.addAll(declaredFields, superField);
        }
        // mybatis plus判断
        boolean plus = parameter.getClass().getDeclaredFields().length == 1 && parameter.getClass().getDeclaredFields()[0].getName().equals("serialVersionUID");

        //兼容mybatis plus
        if (plus) {
            Map<String, Object> updateParam = (Map<String, Object>) parameter;
            Class<?> updateParamType = updateParam.get("param1").getClass();
            declaredFields = updateParamType.getDeclaredFields();
            if (updateParamType.getSuperclass() != null) {
                Field[] superField = updateParamType.getSuperclass().getDeclaredFields();
                declaredFields = ArrayUtil.addAll(declaredFields, superField);
            }
        }
        String fieldName = null;
        for (Field field : declaredFields) {
            fieldName = field.getName();
            if (Objects.equals(CREATED_TIME, fieldName)) {
                if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    field.set(parameter, new Timestamp(System.currentTimeMillis()));
                }
            } else if (Objects.equals(CREATED_BY, fieldName)) {
                if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    field.set(parameter, PaddlingSecurityUtil.getUserId());
                }
            } else if (Objects.equals(LOGIC, fieldName)) {
                if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    field.set(parameter, 0);
                }
            } else if (Objects.equals(MODIFIED_TIME, fieldName)) {
                if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    //兼容mybatis plus的update
                    if (plus) {
                        Map<String, Object> updateParam = (Map<String, Object>) parameter;
                        field.set(updateParam.get("param1"), new Timestamp(System.currentTimeMillis()));
                    } else {
                        field.set(parameter, new Timestamp(System.currentTimeMillis()));
                    }
                }
            } else if (Objects.equals(MODIFIED_BY, fieldName)) {
                if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    //兼容mybatis plus的update
                    if (plus) {
                        Map<String, Object> updateParam = (Map<String, Object>) parameter;
                        field.set(updateParam.get("param1"), PaddlingSecurityUtil.getUserId());
                    } else {
                        field.set(parameter, PaddlingSecurityUtil.getUserId());
                    }
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
