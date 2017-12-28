package org.cx.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author grass
 * @date 2017/12/27
 */
@RestController
public class KafkaProducerController {

    private final KafkaTemplate<Integer, String> kafkaTemplate;

    private final String TOPIC_NAME;

    @Autowired
    public KafkaProducerController(KafkaTemplate<Integer, String> kafkaTemplate, @Value("${kafka.topic}") String topic_name) {
        this.kafkaTemplate = kafkaTemplate;
        TOPIC_NAME = topic_name;
    }

    @GetMapping("/send/msg")
    public boolean sendMsg(@RequestParam String msg) {
        kafkaTemplate.send(TOPIC_NAME, msg);
        return true;
    }
}
