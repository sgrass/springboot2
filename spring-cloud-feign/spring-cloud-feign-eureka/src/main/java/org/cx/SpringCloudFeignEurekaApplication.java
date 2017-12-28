package org.cx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class SpringCloudFeignEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudFeignEurekaApplication.class, args);
	}
}
