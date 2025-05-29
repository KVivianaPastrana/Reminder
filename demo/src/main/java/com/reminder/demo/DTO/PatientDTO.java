package com.reminder.demo.DTO;

public class PatientDTO {
    private int patientId; 
    private String name;
    private String email;

    public PatientDTO(int patientId, String name, String email) {
        this.patientId = patientId;
        this.name = name;
        this.email = email;
    
    }
    public PatientDTO() {
    }
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
