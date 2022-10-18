package com.gcbeen.springmallcommon.exception;

import com.gcbeen.springmallcommon.util.ICode;

public class Asserts {
    
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(ICode resCode) {
        throw new ApiException(resCode);
    }

}
