package org.cx.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.Map;

/**
 * @author grass
 * @date 2017/10/16
 */

/**
 * RestControllerAdvice拦截controller
 */
@RestControllerAdvice(basePackages = "org.cx.web")
public class TestControllerAdvice {

    @ExceptionHandler(value = {NullPointerException.class, IllegalStateException.class, IllegalAccessError.class})
    public Map<String, Object> handleNPE(Throwable throwable) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("message", throwable.getMessage());
        return errors;
    }
}
