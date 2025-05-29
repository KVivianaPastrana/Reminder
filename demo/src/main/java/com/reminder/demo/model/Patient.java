package com.reminder.demo.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Aquí está el auto-increment
	@Column(name = "patientId")
    private int patientId;

    @Column(name = "name")
    private String name;

    @Column(name="email")
    private String email;

    public  Patient(){
    }


    public Patient(int patientId, String name, String email) {
        this.patientId = patientId;
        this.name = name;
        this.email = email;
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