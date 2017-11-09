package org.cx;

import org.cx.interceptor.DefaultHandlerInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SpringBootMvcApplication extends WebMvcConfigurerAdapter implements ErrorPageRegistrar {

	public static void main(String[] args) {
//		SpringApplication.run(SpringBootMvcApplication.class, args);
		SpringApplication springApplication = new SpringApplication(SpringBootMvcApplication.class);
		springApplication.run(args);

	}

	/**
	 * 继承WebMvcConfigurerAdapter 注册拦截器
	 * 或者实现WebMvcConfigurer接口
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new DefaultHandlerInterceptor());
	}

	/**
	 * 实现ErrorPageRegistrar接口 注册异常处理
	 * @param registry
	 */
	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404.htm"));
		registry.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/404.htm"));
	}
}
