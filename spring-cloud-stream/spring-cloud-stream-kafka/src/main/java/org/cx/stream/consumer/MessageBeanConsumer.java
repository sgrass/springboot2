package org.cx.stream.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 几种消费端同时存在时接收顺序如下
 * 1.{@link StreamListener}
 * 2.{@link org.springframework.kafka.annotation.KafkaListener}
 * 3.{@link ServiceActivator}
 * 4.{@link PostConstruct} 标准java回调机制
 *
 * @author grass
 * @date 2017/12/28
 */
@Component
@EnableBinding({Sink.class})
public class MessageBeanConsumer {
    @Autowired
    @Qualifier(Sink.INPUT) // Bean 名称
    private SubscribableChannel subscribableChannel;

    @Autowired
    private Sink sink;

    // 当字段注入完成后的回调,异步监听
    @PostConstruct
    public void init() {
        //实现异步回调
        subscribableChannel.subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println("subscribe: " + message.getPayload());
            }
        });
    }


    /**
     * spring-integration的实现
     * 通过@ServiceActivator
     *
     * @param message
     */
    @ServiceActivator(inputChannel = Sink.INPUT)
    public void onMessage(Object message) {
        System.out.println("@ServiceActivator :" + message);
    }

    /**
     * spring-cloud的实现,屏蔽了具体实现，可任意选择rabbitmq或http等其他实现
     *
     * @param message
     */
    @StreamListener(Sink.INPUT)
    public void onMessage(String message) {
        System.out.println("@StreamListener :" + message);
    }
}
