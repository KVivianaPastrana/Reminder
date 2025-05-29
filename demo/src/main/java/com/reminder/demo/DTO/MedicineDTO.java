package com.reminder.demo.DTO;

public class MedicineDTO {

    private int medicineId;
    private String medicineName;
    private boolean status;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
