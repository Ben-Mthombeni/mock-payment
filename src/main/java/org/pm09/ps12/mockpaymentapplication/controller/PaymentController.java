package org.pm09.ps12.mockpaymentapplication.controller;


import org.pm09.ps12.mockpaymentapplication.dto.PaymentRequest;
import org.pm09.ps12.mockpaymentapplication.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Task 1: Create REST API for payment requests
    @PostMapping("/process")
    public ResponseEntity<Map<String, String>> processPayment(@RequestBody PaymentRequest request) {
        Map<String, String> response = paymentService.processPayment(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{transactionId}")
    public ResponseEntity<Map<String, String>> getStatus(@PathVariable String transactionId) {
        Map<String, String> response = Map.of(
                "transactionId", transactionId,
                "status", "completed",
                "timestamp", String.valueOf(System.currentTimeMillis())
        );
        return ResponseEntity.ok(response);
    }



}