package com.smartclinic.repository;

import com.smartclinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Patient entity, providing CRUD operations and custom queries
 * for the Smart Clinic Management System.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Basic derived query by email
    Optional<Patient> findByEmail(String email);

    // Custom query to find by email or phone
    @Query("SELECT p FROM Patient p WHERE p.email = :email OR p.phone = :phone")
    Optional<Patient> findByEmailOrPhone(String email, String phone);

    // Derived query to find by name (case-insensitive)
    List<Patient> findByNameContainingIgnoreCase(String name);

    // Custom query to find patients by age range
    @Query("SELECT p FROM Patient p WHERE p.dateOfBirth BETWEEN :startDate AND :endDate")
    List<Patient> findByAgeRange(LocalDateTime startDate, LocalDateTime endDate);

    // Derived query to find patients with appointments on a specific date
    @EntityGraph(attributePaths = {"appointments"})
    List<Patient> findByAppointmentsAppointmentDateBetween(LocalDateTime start, LocalDateTime end);

    // Custom query with pagination to retrieve all patients
    @Query("SELECT p FROM Patient p")
    Page<Patient> findAllPatients(Pageable pageable);

    // Derived query to find patients by city
    List<Patient> findByAddressCity(String city);

    // Custom query to count patients by gender
    @Query("SELECT p.gender, COUNT(p) FROM Patient p GROUP BY p.gender")
    List<Object[]> countPatientsByGender();

    // Modifying query to update patient's phone number
    @Modifying
    @Query("UPDATE Patient p SET p.phone = :newPhone WHERE p.id = :patientId")
    int updatePhoneNumber(Long patientId, String newPhone);

    // Derived query to find patients with no appointments
    @Query("SELECT p FROM Patient p WHERE NOT EXISTS (SELECT 1 FROM Appointment a WHERE a.patient.id = p.id)")
    List<Patient> findPatientsWithNoAppointments();

    // Custom query to find patients by multiple criteria (name, email, and city)
    @Query("SELECT p FROM Patient p WHERE p.name LIKE %:name% AND p.email LIKE %:email% AND p.address.city = :city")
    List<Patient> findByMultipleCriteria(String name, String email, String city);

    // Derived query to find the latest registered patient
    @Query("SELECT p FROM Patient p ORDER BY p.createdDate DESC LIMIT 1")
    Optional<Patient> findLatestPatient();

    // Custom query to find patients with appointments in the last 7 days
    @Query("SELECT p FROM Patient p JOIN p.appointments a WHERE a.appointmentDate >= :startDate")
    List<Patient> findPatientsWithRecentAppointments(LocalDateTime startDate);
}
