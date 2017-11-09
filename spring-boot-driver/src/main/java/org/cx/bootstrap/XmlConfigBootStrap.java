package org.cx.bootstrap;

import org.cx.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author grass
 * @date 2017/11/5
 */
public class XmlConfigBootStrap {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();

        applicationContext.setConfigLocations("classpath:/META-INF/spring/context.xml");

        applicationContext.refresh();

        User user = applicationContext.getBean("user", User.class);


        System.out.printf("user.getName() = %s \n",user.getName());

    }
}
