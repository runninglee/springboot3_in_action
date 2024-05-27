package com.sp3.aop.util.api;

public class ResultJson<T> {
    private long code;
    private String message;
    private T data;

    protected ResultJson(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     * ResultJson.success()
     */
    public static <T> ResultJson<T> success() {
        return new ResultJson<>(ResultCode.E_200.getCode(), ResultCode.E_200.getMessage(), null);
    }

    /**
     * 成功返回结果
     * ResultJson.success(data)
     * @param data 获取的数据
     */
    public static <T> ResultJson<T> success(T data) {
        return new ResultJson<>(ResultCode.E_200.getCode(), ResultCode.E_200.getMessage(), data);
    }

    /**
     * 成功返回结果
     * ResultJson.success(data,message)
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> ResultJson<T> success(T data, String message) {
        return new ResultJson<>(ResultCode.E_200.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * ResultJson.failed()
     */
    public static <T> ResultJson<T> failed() {
        return new ResultJson<>(ResultCode.E_500.getCode(), ResultCode.E_500.getMessage(), null);
    }

    /**
     * 失败返回结果
     * ResultJson.failed("操作失败")
     * @param message 提示信息
     */
    public static <T> ResultJson<T> failed(String message) {
        return new ResultJson<>(ResultCode.E_500.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * ResultJson.failed("操作失败")
     * @param message 提示信息
     */
    public static <T> ResultJson<T> failed(String message,long code) {
        return new ResultJson<T>(code, message, null);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> ResultJson<T> validateFailed(String message) {
        return new ResultJson<>(ResultCode.E_422.getCode(), message, null);
    }

    /**
     * 参数验证失败返回结果
     * @param data 提示信息
     */
    public static <T> ResultJson<T> validateFailed(T data) {
        return new ResultJson<>(ResultCode.E_422.getCode(), ResultCode.E_422.getMessage(), data);
    }

    /**
     * 未登录返回结果
     */
    public static <T> ResultJson<T> unauthorized(T data) {
        return new ResultJson<>(ResultCode.E_401.getCode(), ResultCode.E_401.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> ResultJson<T> forbidden(T data) {
        return new ResultJson<>(ResultCode.E_403.getCode(), ResultCode.E_403.getMessage(), data);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}