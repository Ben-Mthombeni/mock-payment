package org.pm09.ps12.mockpaymentapplication.service;


import org.pm09.ps12.mockpaymentapplication.dto.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private MockPaymentProvider paymentProvider;

    public Map<String, String> processPayment(PaymentRequest request) {
        System.out.println("Processing payment for course: " + request.getCourseId());

        // Create payment details
        Map<String, String> paymentDetails = new HashMap<>();
        paymentDetails.put("amount", String.valueOf(request.getAmount()));
        paymentDetails.put("courseId", request.getCourseId());
        paymentDetails.put("cardNumber", maskCardNumber(request.getCardNumber()));
        paymentDetails.put("cardHolder", request.getCardHolder());

        // Call external payment gateway
        String externalResponse = paymentProvider.processPayment(paymentDetails.toString());

        // Prepare response
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("transactionId", UUID.randomUUID().toString());
        response.put("message", "Payment processed successfully");
        response.put("amount", String.valueOf(request.getAmount()));
        response.put("externalResponse", externalResponse);

        return response;
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) return "****";
        return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
    }
}