package org.cx.bootstrap;

import org.cx.config.UserConfig;
import org.cx.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author grass
 * @date 2017/11/5
 */
public class AnnotationConfigBootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 需要注册一个UserConfiguration 的Bean
        applicationContext.register(UserConfig.class);

        applicationContext.refresh();

        User user = applicationContext.getBean("user", User.class);

        System.out.printf("user.getName() = %s \n",user.getName());
    }
}
