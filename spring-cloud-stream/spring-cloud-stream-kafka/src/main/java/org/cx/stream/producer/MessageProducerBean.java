package org.cx.stream.producer;

import org.cx.stream.messaging.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 消息生产者bean
 *
 * Source：来源，近义词：Producer、Publisher
 * Sink：接收器，近义词：Consumer、Subscriber
 * Processor：对于上流而言是 Sink，对于下流而言是 Source
 *
 * @author grass
 * @date 2017/12/27
 */
@Component
@EnableBinding({Source.class, MessageSource.class})
public class MessageProducerBean {

    @Autowired
    @Qualifier(Source.OUTPUT) //bean名称
    private MessageChannel messageChannel;

    @Autowired
    private Source source;

    @Autowired
    @Qualifier(MessageSource.OUTPUT) // Bean 名称
    private MessageChannel cxMessageChannel;


    public void send(String msg) {
        //通过消息管道发送消息 withPayLoad只有消息体，没有消息头。可通过 createMessage()方法设置消息头
//        messageChannel.send(MessageBuilder.withPayload(msg).build());
        //或者使用source发送
        source.output().send(MessageBuilder.withPayload(msg).build());
    }


    public void sendTocx(String msg) {
        cxMessageChannel.send(MessageBuilder.withPayload(msg).build());
    }
}
