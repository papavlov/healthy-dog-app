package com.diplomaproject.healthydog;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogCollarReminderScheduler {

    private static final Logger logger = LoggerFactory.getLogger(DogCollarReminderScheduler.class);

    @Autowired
    private DogCollarReminderService collarReminderService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private DogService dogService;

    @Autowired
    private DogCollarService dogCollarService;


    //@Scheduled(cron = "0 0 0 1 3-10 *") //Scheduled to run on the 1st of March to October at midnight
    //@Scheduled(cron = "0 * * * * *") //every minute - for testing
    @Scheduled(cron = "0 0 0 * * *") // Run every day at midnight
    @Transactional
    public void sendMonthlyCollarReminders() {
        List<User> users = userService.findAllUsers();  // Fetch all users
        for (User user : users) {
            List<Dog> dogs = dogService.findByUser(user);  // Fetch all dogs for each user
            for (Dog dog : dogs) {
                List<DogCollar> recommendedCollars = dogCollarService.getRecommendedCollars(dog.getBreedSize());
                if (!recommendedCollars.isEmpty()) {
                    String reminderMessage = collarReminderService.getCollarReminder(dog, formatCollarList(recommendedCollars));
                    String emailSubject = "Anti-Parasite Collar Reminder for " + dog.getName();
                    try {
                        emailService.sendEmail(user.getEmail(), emailSubject, reminderMessage);
                    } catch (Exception e) {
                        logger.error("Failed to send collar reminder email to {} for dog {}: {}", user.getEmail(), dog.getName(), e.getMessage());
                    }
                }
            }
        }
    }

    private String formatCollarList(List<DogCollar> collars) {
        return collars.stream()
                .map(DogCollar::getCollarName)
                .collect(Collectors.joining(", "));
    }
}

