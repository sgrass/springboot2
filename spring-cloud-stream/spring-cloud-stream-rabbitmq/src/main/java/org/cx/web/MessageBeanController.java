package org.cx.web;

import org.cx.stream.producer.MessageBeanProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author grass
 * @date 2017/12/28
 */
@RestController
public class MessageBeanController {

    private final MessageBeanProducer beanProducer;

    @Autowired
    public MessageBeanController(MessageBeanProducer beanProducer) {
        this.beanProducer = beanProducer;
    }

    @GetMapping("/send/msg/bean")
    public Boolean send(@RequestParam String msg) {
        beanProducer.sendMsg(msg);
        return true;
    }

    @GetMapping("/send/msg/to/cx")
    public Boolean sendTocx(@RequestParam String msg) {
        beanProducer.sendTocx(msg);
        return true;
    }
}
