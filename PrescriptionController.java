package com.smartclinic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
public class PrescriptionController {

    @PostMapping("/api/prescriptions")
    public ResponseEntity<String> savePrescription(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody PrescriptionRequest request) {
        // Token validation
        if (token == null || !token.equals("valid-token-123")) {
            return ResponseEntity.status(401).body("{\"error\": \"Invalid token\"}");
        }

        // Request body validation handled by @Valid
        if (request.getPatientName() == null || request.getMedication() == null) {
            return ResponseEntity.badRequest().body("{\"error\": \"Invalid prescription data\"}");
        }

        // Simulate saving prescription
        String response = "{\"success\": true, \"message\": \"Prescription saved\", " +
                        "\"prescriptionId\": \"pres_001\"}";
        return ResponseEntity.ok(response);
    }
}

// Example request DTO
record PrescriptionRequest(String patientName, String medication) {}
