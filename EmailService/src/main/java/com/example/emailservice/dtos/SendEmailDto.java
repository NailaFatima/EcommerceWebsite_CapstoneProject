package com.example.emailservice.dtos;

import lombok.Getter;
import lombok.Setter;


public class SendEmailDto {
    String emailId;
    String emailMessage;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailMessage() {
        return emailMessage;
    }

    public void setEmailMessage(String emailMessage) {
        this.emailMessage = emailMessage;
    }
}
