package com.reminder.demo.model;

import jakarta.persistence.*;
import java.sql.Time;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordId;

    @ManyToOne
    @JoinColumn(name = "prescriptionId", referencedColumnName = "prescriptionId")
    private Prescription prescription;

    @Column(name = "eventDate")
    private Date eventDate;

    @Column(name = "eventTime")
    private Time eventTime;

    @Column(name = "eventType")
    private String eventType;

    public Record() {}

    public Record(Prescription prescription, Date eventDate, Time eventTime, String eventType) {
        this.prescription = prescription;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventType = eventType;
    }

    public int getRecordId() {
        return recordId;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
