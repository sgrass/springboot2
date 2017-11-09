package org.cx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootWebfluxApplication {

	public static void main(String[] args) {
        /**
         * webflux和controller不能同时存在，同时存在可指定不同启动方式，文档如下
         * https://docs.spring.io/spring-boot/docs/2.0.0.M5/reference/htmlsingle/
         *
         * Adding both spring-boot-starter-web and spring-boot-starter-webflux modules in your application
         * will result in Spring Boot auto-configuring Spring MVC, not WebFlux.
         * This behavior has been chosen because many Spring developers will
         * add spring-boot-starter-webflux to their Spring MVC application to use the reactive WebCLient.
         * You can still enforce your choice by setting the chosen application type like
         * SpringApplication.setWebApplicationType(WebApplicationType.REACTIVE).
         */

        /*SpringApplication springApplication = new SpringApplication(SpringBootWebfluxApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);
        springApplication.run(args);*/

        SpringApplication.run(SpringBootWebfluxApplication.class, args);
    }
}
