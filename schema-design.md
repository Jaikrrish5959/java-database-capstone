# Smart Clinic Management System Database Design

## MySQL Database Design
The MySQL database stores structured data for the Smart Clinic System, ensuring data integrity and relational consistency.

### Table 1: Patients
| Column Name    | Data Type       | Constraints                     |
|----------------|-----------------|---------------------------------|
| patient_id     | INT             | PRIMARY KEY, AUTO_INCREMENT     |
| first_name     | VARCHAR(50)     | NOT NULL                        |
| last_name      | VARCHAR(50)     | NOT NULL                        |
| email          | VARCHAR(100)    | UNIQUE, NOT NULL                |
| phone          | VARCHAR(15)     | NOT NULL                        |
| date_of_birth  | DATE            | NOT NULL                        |

### Table 2: Doctors
| Column Name    | Data Type       | Constraints                     |
|----------------|-----------------|---------------------------------|
| doctor_id      | INT             | PRIMARY KEY, AUTO_INCREMENT     |
| first_name     | VARCHAR(50)     | NOT NULL                        |
| last_name      | VARCHAR(50)     | NOT NULL                        |
| specialty      | VARCHAR(100)    | NOT NULL                        |
| email          | VARCHAR(100)    | UNIQUE, NOT NULL                |

### Table 3: Appointments
| Column Name    | Data Type       | Constraints                     |
|----------------|-----------------|---------------------------------|
| appointment_id | INT             | PRIMARY KEY, AUTO_INCREMENT     |
| patient_id     | INT             | FOREIGN KEY (Patients.patient_id) |
| doctor_id      | INT             | FOREIGN KEY (Doctors.doctor_id) |
| appointment_date | DATETIME      | NOT NULL                        |
| status         | ENUM('Scheduled', 'Completed', 'Cancelled') | NOT NULL |

### Table 4: Admins
| Column Name    | Data Type       | Constraints                     |
|----------------|-----------------|---------------------------------|
| admin_id       | INT             | PRIMARY KEY, AUTO_INCREMENT     |
| username       | VARCHAR(50)     | UNIQUE, NOT NULL                |
| password       | VARCHAR(255)    | NOT NULL                        |
| email          | VARCHAR(100)    | UNIQUE, NOT NULL                |

## MongoDB Collection Design
MongoDB is used for flexible, unstructured data like prescriptions, which may include nested fields or arrays for medications.

### Collection: Prescriptions
**Justification:** MongoDB is ideal for prescriptions due to their variable structure (e.g., different medications, dosages, or instructions per patient).

**Example Document:**
```json
{
  "_id": "prescription_001",
  "patient_id": 1,
  "doctor_id": 2,
  "date_issued": "2025-10-26",
  "medications": [
    {
      "name": "Ibuprofen",
      "dosage": "200mg",
      "frequency": "Twice daily",
      "duration": "7 days"
    },
    {
      "name": "Amoxicillin",
      "dosage": "500mg",
      "frequency": "Three times daily",
      "duration": "10 days"
    }
  ],
  "notes": "Take with food to avoid stomach upset."
}
