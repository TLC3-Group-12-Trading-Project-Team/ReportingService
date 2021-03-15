package com.example.reportingService.redis.resource;

import com.example.reportingService.redis.service.ReportMessagePublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/messages")
@RestController
public class MessageController {
    private final ReportMessagePublisher messagePublisher;

    public MessageController(ReportMessagePublisher reportmessagePublisher){
        this.messagePublisher = reportmessagePublisher;
    }

    @PostMapping
    public ResponseEntity<String> createMessage(@RequestBody String message){
        this.messagePublisher.publish(message);

        return ResponseEntity.ok(message);
    }
}
