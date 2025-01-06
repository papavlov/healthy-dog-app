package com.diplomaproject.healthydog;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogCollarReminderService {

    public String getCollarReminder(Dog dog, String collarNames) {
        // Check if the current month falls within the anti-parasite collar season (March to October)
        Month currentMonth = LocalDate.now().getMonth();
        boolean isCollarSeason = currentMonth.getValue() >= Month.MARCH.getValue() && currentMonth.getValue() <= Month.OCTOBER.getValue();

        if (isCollarSeason) {
            return "It is anti-parasite collar season (March to October). We recommend using the following collars for your dog's protection: "
                    + collarNames + ". Please consult your veterinarian for further details.";
        } else {
            return "Anti-parasite collars are typically needed between March and October. Please check again closer to the season.";
        }
    }
}