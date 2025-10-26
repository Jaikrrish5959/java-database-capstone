package com.smartclinic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @NotNull
    private Patient patient;

    @ManyToOne
    @NotNull
    private Doctor doctor;

    @NotNull
    private LocalDateTime appointmentDate;

    private String status;

    public LocalDateTime getEndTime() {
        return appointmentDate.plusMinutes(30); // Example: 30-minute appointment
    }

    // Getters and setters
}
