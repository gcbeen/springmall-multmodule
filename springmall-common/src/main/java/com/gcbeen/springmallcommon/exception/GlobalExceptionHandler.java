package com.gcbeen.springmallcommon.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcbeen.springmallcommon.util.Result;

@ControllerAdvice
public class GlobalExceptionHandler {
    // ApiException
    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public Result handle(ApiException e) {
        if (e.getResCode() != null) {
            return Result.failed(e.getResCode());
        }
        return Result.failed(e.getMessage());
    }

    // MethodArgumentNotValidException
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handleValidException(MethodArgumentNotValidException e) {
        String message = null;
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + " " + fieldError.getDefaultMessage();
            }
        }
        return Result.validateFailed(message);
    }


    // BindException
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public Result handleValidException(BindException e) {
        String message = null;
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + " " + fieldError.getDefaultMessage();
            }
        }
        return Result.validateFailed(message);
    }

}
