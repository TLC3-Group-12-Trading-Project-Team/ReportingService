package com.TradeProject.ReportingService.redis.service;

import com.TradeProject.ReportingService.redis.dto.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ReportMessageListener implements MessageListener {
    private final ObjectMapper objectMapper;

    public ReportMessageListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            MessageDto msg = objectMapper.readValue(message.getBody(), MessageDto.class);
            log.info(String.valueOf(msg));
            if(msg != null) {
                log.info("Channel: {}, Message: {}", new String(message.getChannel()), msg.getBody());
            }
        } catch (IOException e) {
            log.error("Couldn't convert json", e);
        }
    }
}
