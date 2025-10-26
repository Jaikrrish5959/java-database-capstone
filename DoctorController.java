package com.smartclinic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {

    @GetMapping("/api/doctors/availability")
    public ResponseEntity<String> getDoctorAvailability(
            @RequestHeader("Authorization") String token,
            @RequestParam Long doctorId,
            @RequestParam String date) {
        // Token validation (simplified example)
        if (token == null || !token.equals("valid-token-123")) {
            return ResponseEntity.status(401).body("{\"error\": \"Invalid token\"}");
        }

        // Dynamic parameter processing (e.g., fetch availability for doctorId on date)
        String response = "{\"doctorId\": " + doctorId + ", \"date\": \"" + date + 
                        "\", \"available\": true}";
        return ResponseEntity.ok(response);
    }
}
