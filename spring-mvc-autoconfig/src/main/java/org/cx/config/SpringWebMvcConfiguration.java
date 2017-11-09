package org.cx.config;

import org.cx.annotation.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author grass
 * @date 2017/11/5
 */
@Configuration
@ComponentScan(basePackages = "org.cx")
public class SpringWebMvcConfiguration {

    @ConditionalOnClass(String.class)
    @Bean("helloWorld")
    public String helloWorld(){
        return "helloWorld";
    }

}
