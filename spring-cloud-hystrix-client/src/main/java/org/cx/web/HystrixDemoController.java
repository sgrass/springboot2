package org.cx.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author grass
 * @date 2017/11/21
 */
@RestController
public class HystrixDemoController {

    private final static Random random = new Random();


    /**
     * 当{@link #helloWorld} 方法调用超时或失败时，
     * fallback 方法{@link #errorContent()}作为替代返回
     *
     * HystrixProperty配置参考https://github.com/Netflix/Hystrix/wiki/Configuration
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/hello")
    @HystrixCommand(
            fallbackMethod = "errorContent",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")}
    )
    public String helloWorld() throws InterruptedException {

        //随机数超过100时触发容错，调用errorcontent
        int value = random.nextInt(200);

        System.out.printf("helloWorld() costs %s ms..\n", value);

        TimeUnit.MILLISECONDS.sleep(value);

        return "Hello world";
    }

    private String errorContent() {
        return "Fault";
    }
}
