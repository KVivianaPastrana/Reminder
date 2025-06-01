package com.reminder.demo.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

@Entity
@Table(name = "prescription")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prescriptionId;

    @ManyToOne
    @JoinColumn(name = "patientId", referencedColumnName = "patientId")
        @JsonUnwrapped(prefix = "patient_")  // Desenvuelve las propiedades

    private Patient patient;

      @ManyToOne(fetch = FetchType.EAGER)  // Asegura carga inmediata
    @JoinColumn(name = "medicineId", referencedColumnName = "medicineId")
    @JsonUnwrapped(prefix = "medicine_")  // Corregido el nombre
    private Medicin medicine;

    @OneToMany(mappedBy = "prescription")
    private List<Record> records;

    @Column(name = "schedule")
    private LocalTime schedule;

    @Column(name = "dose")
    private String dose;
    @Column(name = "suspended")
private Boolean suspended = false;          // ← por defecto NO está suspendido

@Column(name = "confirmed")
private Boolean confirmed = false;          // ← por defecto NO confirmado

@Column(name = "reminder_sent_at")
private LocalDateTime reminderSentAt;       // ← nulo hasta que se envíe


    @Column(name = "notified")
private Boolean notified = false;

public Boolean isNotified() {
    return notified;
}

public void setNotified(Boolean notified) {
    this.notified = notified;
}


    public Prescription() {}

    public Prescription(Patient patient, Medicin medicine, LocalTime schedule, String dose, List<Record> records) {
        this.patient = patient;
        this.medicine = medicine;
        this.schedule = schedule;
        this.dose = dose;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
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

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Boolean isSuspended() { return suspended; }
public void setSuspended(Boolean suspended) { this.suspended = suspended; }

public Boolean isConfirmed() { return confirmed; }
public void setConfirmed(Boolean confirmed) { this.confirmed = confirmed; }

public LocalDateTime getReminderSentAt() { return reminderSentAt; }
public void setReminderSentAt(LocalDateTime reminderSentAt) {
    this.reminderSentAt = reminderSentAt;
}

}
