package com.smartclinic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Doctor {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Size(min = 2, max = 100)
    private String specialty;

    private String availableTimes; // Could be a JSON string or List

    // Getters and setters
}
