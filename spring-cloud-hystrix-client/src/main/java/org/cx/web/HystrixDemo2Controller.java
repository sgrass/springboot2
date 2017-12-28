package org.cx.web;

import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author grass
 * @date 2017/11/30
 */
@RestController
public class HystrixDemo2Controller {

    private final static Random random = new Random();

    /**
     * 当方法调用超时或失败时，
     * fallback 方法{@link #errorContent()}作为替代返回
     *
     * @return
     */
    @GetMapping("hello-world-2")
    public String helloWorld2() {
        return new HelloWorldCommand().execute();
    }

    /**
     * 编程方式
     */
    private class HelloWorldCommand extends com.netflix.hystrix.HystrixCommand<String> {

        protected HelloWorldCommand() {
            super(HystrixCommandGroupKey.Factory.asKey("HelloWorld"),
                    100);


        }

        @Override
        protected String run() throws Exception {
            // 如果随机时间 大于 100 ，那么触发容错
            int value = random.nextInt(200);

            System.out.println("helloWorld() costs " + value + " ms.");

            Thread.sleep(value);

            return "Hello,World";
        }

        //容错执行
        @Override
        protected String getFallback() {
            return HystrixDemo2Controller.this.errorContent();
        }
    }


    private String errorContent() {
        return "Fault";
    }
}
