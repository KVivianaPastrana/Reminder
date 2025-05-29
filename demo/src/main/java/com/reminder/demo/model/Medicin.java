package com.reminder.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicine")
public class Medicin {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Aquí está el auto-increment
    @Column(name = "medicineId")
    private Integer medicineId;

    @Column(name = "medicineName")
    private String medicineName;

    @Column(name = "status")
    private boolean status;
    public Medicin(){
    }
    public Medicin(int medicineId, String medicineName, boolean status) {               
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.status = status;           

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

        public boolean getStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
}