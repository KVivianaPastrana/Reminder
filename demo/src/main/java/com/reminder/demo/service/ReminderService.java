package com.reminder.demo.service;


import com.reminder.demo.model.Medicin;
import com.reminder.demo.repository.Imedicine;
import com.reminder.demo.service.EmailService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.reminder.demo.repository.Iprescription;
import com.reminder.demo.model.Prescription;
import java.time.LocalTime;
import java.time.LocalDateTime;  
// Asegúrate de importar esto también
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class ReminderService {

    private static final Logger log =
            LoggerFactory.getLogger(ReminderService.class);

    private final EmailService emailService;
    private final Iprescription prescriptionRepository;
    

    @Autowired
    public ReminderService(EmailService emailService,
                           Iprescription prescriptionRepository) {
        this.emailService = emailService;
        this.prescriptionRepository = prescriptionRepository;
    }
    

    public void enviarRecordatoriosPendientes() {
    LocalTime ahora = LocalTime.now().withSecond(0).withNano(0);
    log.info("⏰ Revisando a las {}", ahora);

    List<Prescription> pendientes = prescriptionRepository.findAll()
        .stream()
        .filter(p -> p != null) // Filtra prescripciones nulas
        .filter(p -> !Boolean.TRUE.equals(p.isSuspended())) // Seguro con nulos
        .filter(p -> !Boolean.TRUE.equals(p.isNotified()))  // Seguro con nulos
        .filter(p -> p.getSchedule() != null) // Filtra horarios nulos
        .filter(p -> p.getSchedule().getHour() == ahora.getHour() && 
                    p.getSchedule().getMinute() == ahora.getMinute())
        .toList();

    for (Prescription p : pendientes) {
        try {
            if (p.getPatient() != null && p.getMedicine() != null) {
                log.info("📨 Enviando recordatorio id={} para las {}", 
                        p.getPrescriptionId(), p.getSchedule());
                
                emailService.sendReminder(
                    p.getPatient().getEmail(),
                    p.getMedicine().getMedicineName(),
                    p.getSchedule().toString(),
                    p.getDose(),
                    p.getPrescriptionId());
                
                p.setNotified(true);
                p.setReminderSentAt(LocalDateTime.now());
                prescriptionRepository.save(p);
            }
        } catch (Exception e) {
            log.error("Error enviando recordatorio para prescripción {}", 
                      p.getPrescriptionId(), e);
        }
    }
}

@Scheduled(fixedRate = 60 * 60 * 1000) // cada hora
public void revisarConfirmacionesPendientes() {
    LocalDateTime ahora = LocalDateTime.now();


    List<Prescription> pendientes =
        prescriptionRepository.findByConfirmedFalseAndReminderSentAtBefore(
                LocalDateTime.now().minusHours(1));

    for (Prescription p : pendientes) {
        log.warn("Paciente {} no confirmó la toma de {} programada para {}", 
                 p.getPatient().getEmail(), 
                 p.getMedicine().getMedicineName(), 
                 p.getSchedule());
        
        emailService.sendMissedDoseNotification(
            p.getPatient().getEmail(),
            p.getMedicine().getMedicineName(),
            p.getSchedule().toString()
        );
    }
}


}
