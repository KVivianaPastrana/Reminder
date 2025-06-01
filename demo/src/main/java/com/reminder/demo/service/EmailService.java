package com.reminder.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    
private static final String BASE_URL = "http://localhost:8085/prescription";

    public void sendReminder(String toEmail, String medicineName, String scheduleTime, String dose, int prescriptionId) {
        String confirmLink = BASE_URL + "/confirm/" + prescriptionId;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("💊 Recordatorio: " + medicineName + " a las " + scheduleTime);
        message.setText(
            "Hola,\n\n" +
            "Es hora de tomar tu medicamento:\n\n" +
            "• Medicamento: " + medicineName + "\n" +
            "• Hora programada: " + scheduleTime + "\n" +
            "• Dosis: " + dose + "\n\n" +
            "Para confirmar que has tomado tu medicamento, por favor haz clic en el siguiente enlace:\n" +
            confirmLink + "\n\n" +
            "¡No olvides seguir tu tratamiento!\n\n" +
            "Saludos,\n" +
            "Sistema de Recordatorios Médicos"
        );
        mailSender.send(message);
    }

    public void sendMissedDoseNotification(String toEmail,String medicineName, String scheduleTime) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(toEmail);
    message.setSubject("💊 Recordatorio: toma de medicamento no confirmada");
    message.setText(
        "Hola,\n\n" +
        "Tu toma de medicamento no se confirmó:\n\n" +
        "• Medicamento: " + medicineName + "\n" +
        "• Hora programada: " + scheduleTime + "\n\n" +
        "Por favor confirma tu toma de medicamento para que puedas tomar tu medicamento.\n\n" +
        "Saludos,\n" +
        "Sistema de Recordatorios Médicos"
    );
    mailSender.send(message);

    }
}
