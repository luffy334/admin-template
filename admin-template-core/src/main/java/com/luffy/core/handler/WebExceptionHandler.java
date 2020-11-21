package com.luffy.core.handler;

import bean.ResultBean;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author luffy
 */
// @RestControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultBean aaa() {
        return null;
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    public ResultBean a1aa() {
        return null;
    }

}
