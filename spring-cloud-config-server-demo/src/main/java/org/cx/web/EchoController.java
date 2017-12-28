package org.cx.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author grass
 * @date 2017/11/12
 */
@RestController
@RefreshScope
public class EchoController {

    @Value("${stu.name}")
    private String name;

    @GetMapping("/name")
    public String getName() {
        return name;
    }
}
