package org.cx.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  grass
 * @data 2017/10/16
 */
@RestController
public class TestController {

    @GetMapping("/index")
    public String index() {
        return "Hello World";
    }

    @GetMapping("/404.html")
    @ResponseBody
    public Map<String, Object> handlerPageNotFound(HttpStatus status, HttpServletRequest request, Throwable throwable) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("statusCode", request.getAttribute("javax.servlet.error.status_code"));
        errors.put("requestUri", request.getAttribute("javax.servlet.error.request_uri"));
        errors.put("aaa","aaa");
        return errors;
    }

    @GetMapping("/npe")
    public Map<String, Object> npe() {
        throw new NullPointerException("故意抛出异常");
    }
}
