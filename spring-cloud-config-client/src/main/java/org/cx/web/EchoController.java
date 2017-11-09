package org.cx.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author grass
 * @date 2017/11/9
 */
@RestController
public class EchoController {

    private final Environment environment;

    @Value("${server.port}")
    private Integer port;

    public EchoController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/echo/env/{name}")
    public Map<String, String> enviroment(@PathVariable String name) {
        Map<String, String> data = new HashMap<>();
        data.put(name, environment.getProperty(name));
//        data.put(name, environment.getProperty("server.port"));
        return data;
    }

    @GetMapping("/echo/env")
    public Environment getEnvironment() {
        return environment;
    }
}

