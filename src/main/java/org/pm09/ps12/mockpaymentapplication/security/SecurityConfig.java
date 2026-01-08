package org.pm09.ps12.mockpaymentapplication.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Task 4: Secure API endpoints
@RestControllerAdvice
class SecurityConfig {

    // Simple API key authentication (for demo purposes)
    @GetMapping("/api/")
    public ResponseEntity<?> secureEndpoint(@RequestHeader(value = "X-API-Key", required = false) String apiKey) {
        if (!"SECRET123".equals(apiKey)) {
            return ResponseEntity.status(401).body("Unauthorized: Invalid API Key");
        }
        return ResponseEntity.ok("Access granted to secure endpoint");
    }
}