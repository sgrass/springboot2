package org.cx.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author grass
 * @date 2017/11/5
 */
@RestController
public class DemoController {

    // 有可能 helloWorld Bean获取不到
    @Autowired(required = false)
    @Qualifier("helloWorld")
    private String helloWorld;

    @GetMapping
    public String index(){
        return helloWorld;
    }
}
