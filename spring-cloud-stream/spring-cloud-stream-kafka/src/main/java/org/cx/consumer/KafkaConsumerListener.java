package org.cx.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * kafka消费者监听器
 * @author grass
 * @date 2017/12/27
 */
@Component
public class KafkaConsumerListener {

    @KafkaListener(topics ="${kafka.topic}")
    public void onMessage(String msg) {
        System.out.println("kafka消费者监听器接收到消息..." + msg);
    }

    @KafkaListener(topics ="cx001")
    public void oncxMessage(String msg) {
        System.out.println("cx通道消费者监听器接收到消息..." + msg);
    }
}
