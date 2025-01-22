package com.diplomaproject.healthydog;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogCollarReminderService {

    public String getCollarReminder(Dog dog, String collarNames) {
        //checking if the current month is off-season (oct-feb)
        Month currentMonth = LocalDate.now().getMonth();
        boolean isOffSeason = currentMonth.getValue() >= Month.OCTOBER.getValue() || currentMonth.getValue() <= Month.FEBRUARY.getValue();

        if (isOffSeason) {
            return "The anti-parasite collar season (March to October) is currently inactive. This is the perfect time to prepare. "
                    + "We recommend the following collars for your dog's protection: " + collarNames + ". Please consult your veterinarian for further details.";
        } else {
            return "Anti-parasite collar season is currently active (March to October). Please ensure your dog is protected.";
        }
    }

}