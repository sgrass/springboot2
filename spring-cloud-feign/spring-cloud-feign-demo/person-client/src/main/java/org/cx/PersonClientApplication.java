package org.cx;

import org.cx.ribbon.FirstServerRule;
import org.cx.service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

/**
 * EnableEurekaClient和EnableDiscoveryClient类似
 * @author grass
 * @date 2017/12/4
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(clients = PersonService.class)
@EnableHystrix
//@RibbonClient(value = "person-service", configuration = PersonClientApplication.class)
public class PersonClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonClientApplication.class);
    }

    @Bean
    public FirstServerRule firstServerRule() {
        return new FirstServerRule();
    }
}
