package org.pm09.ps12.mockpaymentapplication.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MockPaymentProvider {

    private final RestTemplate restTemplate = new RestTemplate();

    // Mock external payment gateway URL
    private final String MOCK_URL = "https://jsonplaceholder.typicode.com/posts";

    public String processPayment(String paymentDetails) {
        System.out.println("Calling external payment API...");

        try {
            // This is a real external API call (to a mock service)
            String response = restTemplate.postForObject(
                    MOCK_URL,
                    paymentDetails,
                    String.class
            );

            System.out.println("Payment API response: " + response);
            return response;

        } catch (Exception e) {
            System.out.println("Mock payment failed: " + e.getMessage());
            // Fallback: Generate mock response
            return "{\"status\":\"success\",\"transactionId\":\"TXN" + System.currentTimeMillis() + "\"}";
        }
    }
}