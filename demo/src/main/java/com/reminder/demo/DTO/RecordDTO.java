package com.reminder.demo.DTO;
import java.sql.Time;
import java.util.Date;
public class RecordDTO {
    private int  recordId;
    private int prescriptionId;
    private Date eventDate;
    private Time eventTime;
    private String eventType;
    public RecordDTO(int recordId, int prescriptionId, Date eventDate, Time eventTime, String eventType) {
        this.recordId = recordId;
        this.prescriptionId = prescriptionId;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventType = eventType;
    }
    public RecordDTO() {
    }
    public int getRecordId() {
        return recordId;
    }
    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
    public int getPrescriptionId() {
        return prescriptionId;
    }
    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
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
