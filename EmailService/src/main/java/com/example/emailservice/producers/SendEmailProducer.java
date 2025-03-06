package com.example.emailservice.producers;

import com.example.emailservice.dtos.SendEmailDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SendEmailProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
//    ObjectMapper is a class that provides functionality for reading and writing JSON, either to and from basic POJOs (Plain Old Java Objects), or to and from a general-purpose
//   JSON Tree Model (JsonNode), as well as related functionality for performing conversions.
    private final ObjectMapper objectMapper;

    private SendEmailProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }
    public String sendEmail(SendEmailDto emailMessage) throws JsonProcessingException {
        kafkaTemplate.send("sendEmail", objectMapper.writeValueAsString(emailMessage));
        //System.out.println("Email sent successfully!");
        return "Email sent successfully!";
    }
}
