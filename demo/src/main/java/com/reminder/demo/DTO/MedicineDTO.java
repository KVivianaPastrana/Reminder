package com.reminder.demo.DTO;

public class MedicineDTO {

    private int medicineId;
    private String medicineName;
    private Boolean status;

    public MedicineDTO(int medicineId) {
        this.medicineId = medicineId;
    }

    public MedicineDTO() {
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
