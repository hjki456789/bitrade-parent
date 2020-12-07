package cn.ztuo.bitrade.config;

import org.springframework.kafka.annotation.*;
import org.springframework.beans.factory.annotation.*;

import java.util.*;

import org.apache.kafka.common.serialization.*;
import org.springframework.kafka.core.*;
import org.springframework.context.annotation.*;

@Configuration
@EnableKafka
public class KafkaProducerConfiguration {
    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;
    @Value("${spring.kafka.producer.retries}")
    private int retries;
    @Value("${spring.kafka.producer.batch.size}")
    private int batchSize;
    @Value("${spring.kafka.producer.linger}")
    private int linger;
    @Value("${spring.kafka.producer.buffer.memory}")
    private int bufferMemory;

    public Map<String, Object> producerConfigs() {
        final Map<String, Object> props = new HashMap<String, Object>();
        props.put("bootstrap.servers", this.servers);
        props.put("retries", this.retries);
        props.put("batch.size", this.batchSize);
        props.put("linger.ms", this.linger);
        props.put("buffer.memory", this.bufferMemory);
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);
        return props;
    }

    public ProducerFactory<String, String> producerFactory() {
        return (ProducerFactory<String, String>) new DefaultKafkaProducerFactory((Map) this.producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return (KafkaTemplate<String, String>) new KafkaTemplate((ProducerFactory) this.producerFactory());
    }
}
