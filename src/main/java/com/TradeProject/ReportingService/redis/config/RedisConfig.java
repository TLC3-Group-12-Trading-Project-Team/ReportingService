package com.TradeProject.ReportingService.redis.config;

import com.TradeProject.ReportingService.redis.service.ReportMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Configuration
public class RedisConfig {
    @Value("${redis.pubsub.channel.name}")
    private String channel;

    private ChannelTopic subChannel = ChannelTopic.of("orderValidation");

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setDefaultSerializer(new StringRedisSerializer());
        template.setConnectionFactory(lettuceConnectionFactory);
        return template;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(LettuceConnectionFactory lettuceConnectionFactory,
                                                                       MessageListenerAdapter messageListenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(lettuceConnectionFactory);
        container.addMessageListener(messageListenerAdapter, subChannel);

        return container;
    }

    @Bean
    public MessageListenerAdapter demoMessageAdapter(ReportMessageListener reportMessageListener){
        return new MessageListenerAdapter(reportMessageListener);
    }

    @Bean
    public ChannelTopic channelTopic() {
        return ChannelTopic.of(channel);
    }
}
