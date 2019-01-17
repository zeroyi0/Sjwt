package com.shiro.sjwtm.common.exception;

import com.shiro.sjwtm.common.utils.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AuthorizationException.class)
    public Result authorizationException() {
        return Result.error("权限不足，请向你爸爸申请");
    }

}
