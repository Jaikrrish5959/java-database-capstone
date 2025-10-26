package com.smartclinic.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class TokenService {

    private final AdminRepository adminRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    // Inject secret key from application.properties
    @Value("${jwt.secret}")
    private String jwtSecret;

    public TokenService(AdminRepository adminRepository, DoctorRepository doctorRepository, 
                       PatientRepository patientRepository) {
        this.adminRepository = adminRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public String generateToken(String identifier) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 7 * 24 * 60 * 60 * 1000); // 7 days expiration

        return Jwts.builder()
                .setSubject(identifier)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    public String extractIdentifier(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token, String userType) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();
            String identifier = claims.getSubject();

            // Validate based on user type
            return switch (userType.toLowerCase()) {
                case "admin" -> adminRepository.findByUsername(identifier).isPresent();
                case "doctor" -> doctorRepository.findByEmail(identifier).isPresent();
                case "patient" -> patientRepository.findByEmail(identifier).isPresent();
                default -> false;
            };
        } catch (Exception e) {
            return false; // Token is invalid or expired
        }
    }

    public SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
}
