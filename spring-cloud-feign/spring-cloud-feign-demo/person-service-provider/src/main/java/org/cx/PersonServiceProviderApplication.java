package org.cx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author grass
 * @date 2017/12/4
 */
@SpringBootApplication
@EnableEurekaClient
public class PersonServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonServiceProviderApplication.class);
    }
}
