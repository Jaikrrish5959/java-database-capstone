package com.smartclinic.controller;

import com.smartclinic.model.Doctor;
import com.smartclinic.service.DoctorService; // Assume this exists
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PatientController {

    private final DoctorService doctorService;
    private final TokenService tokenService;

    public PatientController(DoctorService doctorService, TokenService tokenService) {
        this.doctorService = doctorService;
        this.tokenService = tokenService;
    }

    @GetMapping("/patient/login")
    public String showLoginForm() {
        return "patient-login";
    }

    @PostMapping("/patient/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model, RedirectAttributes redirectAttributes) {
        // Simulate authentication (replace with PatientRepository)
        if ("john.doe@example.com".equals(email) && "password123".equals(password)) {
            String token = tokenService.generateToken(email);
            redirectAttributes.addFlashAttribute("token", token);
            return "redirect:/patient/dashboard";
        }
        model.addAttribute("error", "Invalid credentials");
        return "patient-login";
    }

    @GetMapping("/patient/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("token", model.getAttribute("token")); // From flash attribute
        return "patient-dashboard";
    }

    @GetMapping("/patient/search-doctor")
    public String searchDoctor(@RequestParam String name, Model model) {
        // Simulate searching for a doctor (replace with DoctorRepository)
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Smith");
        doctor.setSpecialty("Cardiology");
        model.addAttribute("doctors", java.util.Collections.singletonList(doctor));
        return "patient-dashboard";
    }
}
