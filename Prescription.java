package com.smartclinic.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Document(collection = "prescriptions")
public class Prescription {
    @Id
    private String id;

    @NotNull
    private String patientName;

    private List<Medication> medication; // Nested medication list

    private Long appointmentId;

    // Nested class for medication
    public static class Medication {
        private String name;
        private String dosage;
        private String frequency;

        // Getters and setters
    }

    // Getters and setters
}
