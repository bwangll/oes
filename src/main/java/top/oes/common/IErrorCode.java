package top.oes.common;

/**
 * @author bwang
 * @version 1.0.0
 * @Description IErrorCode
 * @CreateTime 2021/9/20 8:09 下午
 */
public interface IErrorCode {
    /**
     * 获取状态码
     * @return code
     */
    long getCode();

    /**
     * 获取消息
     * @return message
     */
    String getMessage();
}
