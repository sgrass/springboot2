package org.cx;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCloudConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SpringCloudConfigClientApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);

//		SpringApplication.run(SpringCloudConfigClientApplication.class, args);
	}
}
