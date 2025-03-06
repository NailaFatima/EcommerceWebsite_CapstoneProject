package com.example.emailservice.consumers;

import com.example.emailservice.dtos.SendEmailDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SendEmailConsumer {
    private final ObjectMapper objectMapper;
    public SendEmailConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @KafkaListener(topics = "sendEmail", groupId = "emailService")
    public void consume(String message) throws JsonProcessingException {
        SendEmailDto sendEmailDto = objectMapper.readValue(message, SendEmailDto.class);
        System.out.println("Email sent to: " + sendEmailDto.getEmailId());
        System.out.println("Email message: " + sendEmailDto.getEmailMessage());
    }
}
