package org.cx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.PropertySource;

/**
 * EnableHystrix 激活：不带spring cloud，如 /hystrix.stream 端点
 *
 * EnableCircuitBreaker  激活 ：@EnableHystrix + Spring Cloud 功能
 *
 * EnableHystrixDashboard 开启hystrixDashboard
 *
 * {@link PropertySource}   也支持多个properties文件
 * {@link org.springframework.context.annotation.PropertySources} 支持加载多个properties文件，且可单独设置文件编码
 */
@SpringBootApplication
//@EnableHystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
@PropertySource("config.properties")  //加载文件
public class SpringCloudHystrixClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudHystrixClientApplication.class, args);
	}
}
