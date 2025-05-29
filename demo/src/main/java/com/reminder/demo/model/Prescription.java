package com.reminder.demo.model;


import jakarta.persistence.*;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "prescription")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prescriptionId;

    @ManyToOne
    @JoinColumn(name = "patientId", referencedColumnName = "patientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medicineId", referencedColumnName = "medicineId")
    private Medicin medicine;

    @Column(name = "schedule")
    private Time schedule;

    @Column(name = "dose")
    private String dose;

    public Prescription() {}

    public Prescription(Patient patient, Medicin medicine, Time schedule, String dose) {
        this.patient = patient;
        this.medicine = medicine;
        this.schedule = schedule;
        this.dose = dose;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medicin getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicin medicine) {
        this.medicine = medicine;
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
