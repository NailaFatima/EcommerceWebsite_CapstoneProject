package com.example.emailservice.controllers;

import com.example.emailservice.dtos.SendEmailDto;
import com.example.emailservice.producers.SendEmailProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.producer.internals.Sender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    private final SendEmailProducer sendEmailProducer;
    public EmailController(SendEmailProducer sendEmailProducer){
        this.sendEmailProducer = sendEmailProducer;
    }
    @PostMapping("/send")
    public String sendEmail(@RequestBody SendEmailDto message) throws JsonProcessingException {
        return sendEmailProducer.sendEmail(message);
    }
}
