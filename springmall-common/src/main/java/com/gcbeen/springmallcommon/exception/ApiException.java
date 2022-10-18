package com.gcbeen.springmallcommon.exception;

import com.gcbeen.springmallcommon.util.ICode;

/**
 * 自定义 API 异常
 */
public class ApiException extends RuntimeException {
    private ICode resCode;

    public ApiException(ICode resCode) {
        super(resCode.getMessage());
        this.resCode = resCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ICode getResCode() {
        return resCode;
    }

}
