package top.oes.common;

/**
 * @author bwang
 * @version 1.0.0
 * @Description ResultCode
 * @CreateTime 2021/9/20 8:09 下午
 */
public enum ResultCode implements IErrorCode{
    /** success */
    SUCCESS(200, "操作成功"),
    /** failed */
    FAILED(500, "操作失败"),
    /** 404 */
    VALIDATE_FAILED(404, "参数检验失败"),
    /** 401 */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    /** 403 */
    FORBIDDEN(403, "没有相关权限");

    private final long code;
    private final String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
