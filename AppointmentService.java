package com.smartclinic.service;

import com.smartclinic.model.Appointment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    private List<Appointment> appointments = new ArrayList<>();

    public Appointment bookAppointment(Appointment appointment) {
        // Simulate saving to database
        appointments.add(appointment);
        return appointment;
    }

    public List<Appointment> getAppointmentsByDoctorAndDate(Long doctorId, LocalDateTime date) {
        // Simulate retrieval logic
        return appointments.stream()
                .filter(a -> a.getDoctor().getId().equals(doctorId) && 
                           a.getAppointmentDate().toLocalDate().equals(date.toLocalDate()))
                .toList();
    }
}
