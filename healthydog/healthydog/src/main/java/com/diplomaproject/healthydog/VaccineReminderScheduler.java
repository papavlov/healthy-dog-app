package com.diplomaproject.healthydog;

import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineReminderScheduler {

    private static final Logger logger = LoggerFactory.getLogger(VaccineReminderScheduler.class);

    @Autowired
    private VaccineReminderService vaccineReminderService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private DogService dogService;

    // Scheduled to run once a day at midnight
   // @Scheduled(cron = "0 0 0 * * *") // Run every day at midnight
    @Scheduled(cron = "0 * * * * *")//every minute
    public void sendDailyVaccineReminders() {
        List<User> users = userService.findAllUsers();  // Fetch all users
        for (User user : users) {
            List<Dog> dogs = dogService.findByUser(user);  // Fetch all dogs for each user
            for (Dog dog : dogs) {
                // Get the vaccine reminder message for the dog using VaccineReminderService
                String reminderMessage = vaccineReminderService.getVaccineReminder(dog);
                if (reminderMessage != null) {
                    // Send the reminder via email to the user
                    String emailSubject = "Vaccine Reminder for " + dog.getName();
                    try {
                        emailService.sendEmail(user.getEmail(), emailSubject, reminderMessage);  // Handle any exception
                    } catch (Exception e) {
                        // Log the error (using SLF4J instead of System.err)
                        logger.error("Failed to send email to {} for dog {}: {}", user.getEmail(), dog.getName(), e.getMessage());
                    }
                }
            }
        }
    }

}
