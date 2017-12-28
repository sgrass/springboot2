package org.cx.stream.producer;

import org.cx.stream.messaging.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author grass
 * @date 2017/12/28
 */
@Component
@EnableBinding({Source.class, MessageSource.class})
public class MessageBeanProducer {

    @Autowired
    @Qualifier(Source.OUTPUT)
    private MessageChannel messageChannel;

    @Autowired
    private Source source;


    /**
     * 配置自定义source
     */
    @Autowired
    private MessageSource messageSource;
    @Autowired
    @Qualifier(MessageSource.OUTPUT)
    private MessageChannel cxMessageChannel;



    public void sendMsg(String msg) {
        source.output().send(MessageBuilder.withPayload(msg).build());
    }

    public void sendTocx(String msg) {
        cxMessageChannel.send(MessageBuilder.withPayload(msg).build());
    }

}
