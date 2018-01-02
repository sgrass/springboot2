package org.cx;

import org.cx.web.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@Import(WebConfig.class)
@EnableTransactionManagement(proxyTargetClass = true)   //使用接口做代理 还是使用类来做代理
public class PersonServiceProviderApp {

    public static void main(String[] args) {
        SpringApplication.run(PersonServiceProviderApp.class, args);
    }
}
