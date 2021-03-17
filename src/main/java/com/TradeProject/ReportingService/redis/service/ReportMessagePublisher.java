package com.TradeProject.ReportingService.redis.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;


@Service
public class ReportMessagePublisher {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic topic;

    public ReportMessagePublisher(RedisTemplate<String, Object> redisTemplate, ChannelTopic topic){
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public void publish(String message){
        this.redisTemplate.convertAndSend(topic.getTopic(), message);
    }

}

