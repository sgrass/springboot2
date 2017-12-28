package org.cx.simple.demo;

import kafka.utils.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.cx.simple.KafkaProperties;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

/**
 * @author grass
 * @date 2017/12/27
 */
public class KafkaConsumerDemo extends ShutdownableThread{

    private final static String TOPIC_NAME = "test119";

    private final KafkaConsumer<Integer, String> consumer;

    public KafkaConsumerDemo() {
        super("kafkaConsumerTest", false);
        Properties properties=new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,KafkaProperties.KAFKA_BROKER_LIST);
        //GroupId 消息所属的分组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"DemoGroup1");
        //是否自动提交消息:offset
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
        //自动提交的间隔时间
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
        //设置使用最开始的offset偏移量为当前group.id的最早消息
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        //设置心跳时间
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,"30000");
        //对key和value设置反序列化对象
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        this.consumer = new KafkaConsumer<Integer, String>(properties);

        //消费指定分区,指定分区不能和consumer.subscribe订阅同时出现
//        TopicPartition p0 = new TopicPartition(KafkaProperties.TOPIC, 0);
//        this.consumer.assign(Arrays.asList(p0));
    }

    @Override
    public void doWork() {
        //订阅
        consumer.subscribe(Collections.singletonList(TOPIC_NAME));
        //获得消息,poll是阻塞
        ConsumerRecords<Integer, String> records = consumer.poll(1000);

        for (ConsumerRecord<Integer, String> record : records) {
            System.out.printf("[%s] receiver message: key:[%s]-->value[%s], offset[%s] \n",
                    record.partition(), record.key(), record.value(), record.offset());
        }
    }


    public static void main(String[] args) throws IOException {
       KafkaConsumerDemo consumerDemo = new KafkaConsumerDemo();
       consumerDemo.start();
       /**
        * 默认50个partition
        * hashcode的绝对值%50, 查看consumer group 的offset存储在哪个__consumer_offset
       */
//        System.out.println(Math.abs("demogroup1".hashCode()) % 50);
    }
}
