package org.cx.simple.demo;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.cx.simple.KafkaProperties;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 原生api操作
 * @author grass
 * @date 2017/12/27
 */
public class KafkaProducerDemo {

    private final static String TOPIC_NAME = "test119";

    private final KafkaProducer<Integer, String> producer;

    public KafkaProducerDemo() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", KafkaProperties.KAFKA_BROKER_LIST);
        props.put("key.serializer", IntegerSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        this.producer = new KafkaProducer<Integer, String>(props);
    }

    public void sendMsg(String msg) {
        Future<RecordMetadata> metadataFuture = producer.send(new ProducerRecord<Integer, String>(TOPIC_NAME, 1, msg), new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                System.out.printf("message send to:[%s], offset:[%s] \n", metadata.partition(), metadata.offset());
            }
        });

        try {
            //阻塞
            metadataFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        KafkaProducerDemo producerDemo = new KafkaProducerDemo();
        producerDemo.sendMsg("hello world");

    }
}
