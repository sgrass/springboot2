package org.cx.stream.consumer;

import org.cx.stream.messaging.MessageSource;
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


    @PostConstruct
    public void init() {
        subscribableChannel.subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println("subscribe : " + message.getPayload());
            }
        });
    }



    @ServiceActivator(inputChannel = Sink.INPUT)
    public void onMsg(Object msg) {
        System.out.println("@ServiceActivator :" + msg);
    }


    @StreamListener(Sink.INPUT)
    public void onMsg(String msg) {
        System.out.println("@StreamListener :" + msg);
    }

    /**
     * 监听自定义主题cx
     * @param msg
     */
    @StreamListener(MessageSource.OUTPUT)
    public void onMsgCx(String msg) {
        System.out.println("@StreamListener--cx :" + msg);
    }
}
