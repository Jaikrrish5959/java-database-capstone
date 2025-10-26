# User Stories for Smart Clinic Management System

This document outlines the user stories for the Smart Clinic Management System, detailing the requirements and acceptance criteria for the Doctor, Patient, and Admin roles. These stories are designed to guide the development and testing of the system as of 12:00 AM IST on Monday, October 27, 2025.

## Doctor User Stories

### 1. Doctor Login
- **As a Doctor**, I want to log in to the system with my credentials, so that I can access my personalized dashboard.
- **Acceptance Criteria:**
  - The system provides a login page at `/doctor/login`.
  - Valid credentials (e.g., email and password) authenticate me using JWT tokens.
  - Upon successful login, I am redirected to `/doctor/dashboard`.
  - Invalid credentials display an error message.

### 2. View Patient Appointments
- **As a Doctor**, I want to view a list of all my patient appointments, so that I can manage my schedule effectively.
- **Acceptance Criteria:**
  - The dashboard at `/doctor/dashboard` displays a table of appointments with columns: Patient Name, Date, and Status.
  - Appointments are filtered by my doctor ID and the current date (e.g., 2025-10-27) by default.
  - I can see details like "John Doe - 2025-10-27 09:00:00 - Scheduled".

### 3. Check Availability
- **As a Doctor**, I want to check my availability for a specific date, so that I can inform patients or admins about open slots.
- **Acceptance Criteria:**
  - A GET endpoint `/api/doctors/availability` accepts `doctorId` and `date` parameters.
  - The system returns a list of available time slots (e.g., ["09:00", "10:00"]) based on my schedule and existing appointments.
  - The response is secured with a valid JWT token.

## Patient User Stories

### 4. Patient Login
- **As a Patient**, I want to log in to the system with my credentials, so that I can access my personalized dashboard.
- **Acceptance Criteria:**
  - The system provides a login page at `/patient/login`.
  - Valid credentials (e.g., email and password) authenticate me using JWT tokens.
  - Upon successful login, I am redirected to `/patient/dashboard`.
  - Invalid credentials display an error message.

### 5. Search for a Doctor
- **As a Patient**, I want to search for a doctor by name, so that I can find a suitable healthcare provider.
- **Acceptance Criteria:**
  - The dashboard at `/patient/dashboard` includes a search form that sends a GET request to `/patient/search-doctor`.
  - Entering a name (e.g., "Dr. Smith") returns a list of matching doctors with their specialties (e.g., "Dr. Smith - Cardiology").
  - The search is case-insensitive and displays results in real-time.

### 6. View Booked Appointments
- **As a Patient**, I want to view my booked appointments, so that I can keep track of my healthcare visits.
- **Acceptance Criteria:**
  - A GET endpoint (e.g., `/api/appointments/patient`) returns a list of my appointments when authenticated with my credentials.
  - The dashboard or a dedicated page displays details like "Dr. Smith - 2025-10-27 09:00:00 - Scheduled".
  - The list is filtered by my patient ID.

## Admin User Stories

### 7. Admin Login
- **As an Admin**, I want to log in to the system with my credentials, so that I can manage clinic operations.
- **Acceptance Criteria:**
  - The system provides a login page at `/admin/login`.
  - Valid credentials (e.g., username and password) authenticate me using JWT tokens.
  - Upon successful login, I am redirected to `/admin/dashboard`.
  - Invalid credentials display an error message.

### 8. Add a New Doctor
- **As an Admin**, I want to add a new doctor to the system, so that the clinic can expand its medical staff.
- **Acceptance Criteria:**
  - The dashboard at `/admin/dashboard` includes a form to input doctor details (name, specialty, email).
  - Submitting the form via POST to `/admin/add-doctor` saves the doctor to the database.
  - A success message (e.g., "Doctor Dr. New Doctor added successfully!") is displayed after submission.

### 9. Generate Appointment Reports
- **As an Admin**, I want to generate daily, monthly, and yearly reports on doctor appointments, so that I can analyze clinic performance.
- **Acceptance Criteria:**
  - The system provides stored procedures: `GetDailyAppointmentReportByDoctor`, `GetDoctorWithMostPatientsByMonth`, `GetDoctorWithMostPatientsByYear`.
  - Running `CALL GetDailyAppointmentReportByDoctor('2025-10-26')` returns a count of appointments per doctor for the current date.
  - Running `CALL GetDoctorWithMostPatientsByMonth('2025-10')` and `CALL GetDoctorWithMostPatientsByYear('2025')` return the doctor with the most patients for the specified period.

## Additional Notes
- **Time Context:** The current date is October 27, 2025, and the system should handle time-sensitive operations (e.g., defaulting to 2025-10-27 for daily reports unless otherwise specified).
- **Security:** All login and sensitive operations require JWT token validation using the `TokenService`.
- **Integration:** These stories are implemented using the provided controllers (`AdminController`, `DoctorController`, `PatientController`), services (`DoctorService`, `AppointmentService`, `TokenService`), and Thymeleaf templates.
- **Testing:** Each story can be tested via the UI (e.g., `/admin/dashboard`) or API (e.g., `/api/doctors/availability`) and validated against the acceptance criteria.

## Implementation Status
- **Completed:** Login functionalities, doctor availability endpoint, patient search, admin doctor addition (based on provided code).
- **Pending:** Stored procedures for reports (to be added to MySQL `cms` database).
