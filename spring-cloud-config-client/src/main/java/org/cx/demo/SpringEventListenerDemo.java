package org.cx.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author grass
 * @date 2017/11/6
 */
public class SpringEventListenerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册监听器，listener只关注MyApplicationListener事件
        applicationContext.addApplicationListener(new ApplicationListener<MyApplicationListener>() {
            //监听器得到事件
            @Override
            public void onApplicationEvent(MyApplicationListener event) {
                System.out.printf("接收到事件 %s @ %s\n", event.getSource(), event.getApplicationContext());
            }
        });

        applicationContext.refresh();

        //发布事件
        applicationContext.publishEvent(new MyApplicationListener(applicationContext, "hello,world"));
        applicationContext.publishEvent(new MyApplicationListener(applicationContext, 1));
        applicationContext.publishEvent(new MyApplicationListener(applicationContext, new Integer(100)));

    }

    private static class MyApplicationListener extends ApplicationEvent {

        private final ApplicationContext applicationContext;
        /**
         * Create a new ApplicationEvent.
         *
         * @param source the object on which the event initially occurred (never {@code null})
         */
        public MyApplicationListener(ApplicationContext applicationContext, Object source) {
            super(source);
            this.applicationContext = applicationContext;
        }

        public ApplicationContext getApplicationContext() {
            return applicationContext;
        }
    }
}
