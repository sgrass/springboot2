package org.cx;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.cx.ribbon.FirstServerForeverRule;
import org.cx.service.PersonService;
import org.cx.web.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author grass
 * @date 2017/12/29
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(clients = PersonService.class)
@EnableHystrix
//@RibbonClient(value = "person-service", configuration = PersonClientApplication.class)
@Import(WebConfig.class)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public FirstServerForeverRule firstServerForeverRule() {
        return new FirstServerForeverRule();
    }
}
