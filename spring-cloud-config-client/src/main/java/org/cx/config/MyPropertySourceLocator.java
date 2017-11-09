package org.cx.config;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author grass
 * @date 2017/11/9
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyPropertySourceLocator implements PropertySourceLocator {

    @Override
    public PropertySource<?> locate(Environment environment) {
        Map<String, Object> source = new HashMap<>();
        source.put("server.port", "9090");
        MapPropertySource propertySource = new MapPropertySource("my-property-source", source);
        return propertySource;
    }

}
