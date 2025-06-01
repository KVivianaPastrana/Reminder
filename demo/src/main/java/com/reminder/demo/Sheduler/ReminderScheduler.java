package com.reminder.demo.Sheduler;

import java.time.LocalTime;
import org.slf4j.Logger;                      // 🆕
import org.slf4j.LoggerFactory;              // 🆕
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.reminder.demo.service.ReminderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.reminder.demo.service.EmailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class ReminderScheduler {

    private final ReminderService reminderService;

    public ReminderScheduler(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    /**  →  segundo 0 de cada minuto, zona Bogotá  */
    @Scheduled(cron = "0 * * * * *", zone = "America/Bogota")
    public void run() {
        reminderService.enviarRecordatoriosPendientes();
    }
}

