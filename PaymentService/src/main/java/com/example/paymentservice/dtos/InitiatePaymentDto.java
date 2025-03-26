package com.example.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentDto {
String email;
String phoneNumber;
String orderId;
Long amount;
}
