package xyz.weechang.paddling.core.error;

/**
 * 说明：
 *
 * @author zhangwei
 * date 2017年11月18日23:45:37
 */
public interface IError {

    /**
     * 获取nameSpace
     *
     * @return nameSpace
     */
    String getNs();

    /**
     * 获取错误码

     * @return 错误码
     */
    int getCode();

    /**
     * 获取错误信息

     * @return 错误信息
     */
    String getMsg();
}
