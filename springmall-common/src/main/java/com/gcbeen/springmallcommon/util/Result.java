package com.gcbeen.springmallcommon.util;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    @ApiModelProperty(value = "状态码")
    private long code;
    @ApiModelProperty(value = "状态描述信息")
    private String message;
    @ApiModelProperty(value = "数据")
    private T data;

    // 成功返回结果
    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    // 成功返回结果
    public static <T> Result<T> success(T data, String message) {
        return new Result<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    // 失败返回结果
    public static <T> Result<T> failed(ICode failedCode) {
        return new Result<T>(failedCode.getCode(), failedCode.getMessage(), null);
    }

    // 失败返回结果
    public static <T> Result<T> failed() {
        return new Result<T>(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage(), null);
    }

    // 失败返回结果
    public static <T> Result<T> failed(String message) {
        return new Result<T>(ResultCode.FAILED.getCode(), message, null);
    }

    // 参数验证失败返回结果
    public static <T> Result<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    // 参数验证失败返回结果
    public static <T> Result<T> validateFailed(String message) {
        return new Result<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    // 未登录返回结果
    public static <T> Result<T> unauthorized(T data) {
        return new Result<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    // 未授权返回结果
    public static <T> Result<T> forbidden(T data) {
        return new Result<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

}
