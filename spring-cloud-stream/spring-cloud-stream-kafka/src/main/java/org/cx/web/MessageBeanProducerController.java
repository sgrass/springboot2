package org.cx.web;

import org.cx.stream.producer.MessageProducerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author grass
 * @date 2017/12/27
 */
@RestController
public class MessageBeanProducerController {

    private final MessageProducerBean messageProducerBean;


    @Autowired
    public MessageBeanProducerController(MessageProducerBean messageProducerBean) {
        this.messageProducerBean = messageProducerBean;
    }

    @GetMapping("/send/msg/bean")
    public boolean send(@RequestParam String msg) {
        messageProducerBean.send(msg);
        return true;
    }

    @GetMapping("/send/msg/to/cx")
    public boolean sendTocx(@RequestParam String msg) {
        messageProducerBean.sendTocx(msg);
        return true;
    }
}
