package com.example.paymentservice.controllers;

import com.example.paymentservice.dtos.InitiatePaymentDto;
import com.example.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping
    public String initiatePayment(@RequestBody InitiatePaymentDto initiatePaymentDto) {
        return paymentService.initiatePayment(
                initiatePaymentDto.getEmail(),
                initiatePaymentDto.getPhoneNumber(),
                initiatePaymentDto.getAmount(),
                initiatePaymentDto.getOrderId());
    }
//Download localtunnel expose tool. This will create temporary url to test webhook.
//Run the following command in terminal:
//lt --port 8080
//This will create a temporary url. Use this url to create webhook in stripe/razorpay dashboard.
    //On Dashboard go to account and setting->webhooks->add webhook->paste the url->select events(paymentEvents_links->add webhook.
//After creating webhook, make a payment in stripe/razorpay dashboard. You will see the webhook event in the terminal.
    @PostMapping("/webhook")
    public String listenToWebhook(@RequestBody String webhookEvent) {
        System.out.println(webhookEvent);
        return "OK";
    }
}
