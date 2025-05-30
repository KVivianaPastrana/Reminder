package com.reminder.demo.DTO;

import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PrescriptionDTO {
    private int prescriptionId;
    private int patientId;
    private String patientName;    // <-- nuevo
    private int medicineId;
    private String medicineName;   // <-- nuevo

    @JsonFormat(pattern = "HH:mm")
    private LocalTime schedule;

    private String dose;

    // Constructor con nuevos campos agregados
    public PrescriptionDTO(int prescriptionId, int patientId, String patientName, int medicineId, String medicineName, LocalTime schedule, String dose) {    
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.schedule = schedule;
        this.dose = dose;
    }

    public PrescriptionDTO() {}

    // Getters y setters para los nuevos campos

    public int getPrescriptionId() {
        return prescriptionId;
    }   
    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getMedicineId() {
        return medicineId;
    }
    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public LocalTime getSchedule() {
        return schedule;
    }
    public void setSchedule(LocalTime schedule) {
        this.schedule = schedule;
    }

    public String getDose() {
        return dose;
    }
    public void setDose(String dose) {
        this.dose = dose;
    }
}
