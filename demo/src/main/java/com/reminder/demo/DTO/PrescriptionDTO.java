package com.reminder.demo.DTO;
import java.sql.Time;
public class PrescriptionDTO {
    private int prescriptionId;
    private int patientId;
    private int medicineId;
    private Time schedule;
    private  String dose;

    public PrescriptionDTO(int prescriptionId, int patientId, int medicineId, Time schedule, String dose) {    
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.medicineId = medicineId;
        this.schedule = schedule;
        this.dose = dose;
    }
    public PrescriptionDTO() {
    }

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
    public int getMedicineId() {
        return medicineId;
    }
    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }
    public Time getSchedule() {
        return schedule;
    }
    public void setSchedule(Time schedule) {
        this.schedule = schedule;
    }
    public String getDose() {
        return dose;
    }
    public void setDose(String dose) {
        this.dose = dose;
    }


}
