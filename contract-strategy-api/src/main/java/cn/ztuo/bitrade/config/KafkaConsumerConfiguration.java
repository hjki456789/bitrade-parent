package cn.ztuo.bitrade.config;

import org.springframework.kafka.annotation.*;
import org.springframework.beans.factory.annotation.*;

import java.util.*;

import org.apache.kafka.common.serialization.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.*;
import org.springframework.kafka.config.*;
import org.springframework.context.annotation.*;

@Configuration
@EnableKafka
public class KafkaConsumerConfiguration {
    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;
    @Value("${spring.kafka.consumer.enable.auto.commit}")
    private boolean enableAutoCommit;
    @Value("${spring.kafka.consumer.session.timeout}")
    private String sessionTimeout;
    @Value("${spring.kafka.consumer.auto.commit.interval}")
    private String autoCommitInterval;
    @Value("${spring.kafka.consumer.group.id}")
    private String groupId;
    @Value("${spring.kafka.consumer.auto.offset.reset}")
    private String autoOffsetReset;
    @Value("${spring.kafka.consumer.concurrency}")
    private int concurrency;
    @Value("${spring.kafka.consumer.maxPollRecordsConfig}")
    private int maxPollRecordsConfig;

    public Map<String, Object> consumerConfigs() {
        final Map<String, Object> propsMap = new HashMap<String, Object>();
        propsMap.put("bootstrap.servers", this.servers);
        propsMap.put("enable.auto.commit", this.enableAutoCommit);
        propsMap.put("auto.commit.interval.ms", this.autoCommitInterval);
        propsMap.put("session.timeout.ms", this.sessionTimeout);
        propsMap.put("key.deserializer", StringDeserializer.class);
        propsMap.put("value.deserializer", StringDeserializer.class);
        propsMap.put("group.id", this.groupId);
        propsMap.put("auto.offset.reset", this.autoOffsetReset);
        propsMap.put("max.poll.records", this.maxPollRecordsConfig);
        return propsMap;
    }

    public ConsumerFactory<String, String> consumerFactory() {
        return (ConsumerFactory<String, String>) new DefaultKafkaConsumerFactory((Map) this.consumerConfigs());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        final ConcurrentKafkaListenerContainerFactory<String, String> factory = (ConcurrentKafkaListenerContainerFactory<String, String>) new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(this.consumerFactory());
        factory.setConcurrency(Integer.valueOf(this.concurrency));
        factory.getContainerProperties().setPollTimeout(1500L);
        factory.setBatchListener(Boolean.valueOf(true));
        return factory;
    }
}
