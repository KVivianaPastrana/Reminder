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
    private Boolean status;
    public Medicin(){
    }
    public Medicin(Integer medicineId, String medicineName, Boolean status) {               
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.status = status;           

    }

    public Integer getMedicineId() {        
        return medicineId;          
        }   

        public void setMedicineId(Integer  medicineId) {
            this.medicineId = medicineId;
        }       
        public String getMedicineName() {
            return medicineName;
        }

        public void setMedicineName(String medicineName) {
            this.medicineName = medicineName;
        }       

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }
}