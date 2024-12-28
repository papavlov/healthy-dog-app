package com.diplomaproject.healthydog;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class VaccineReminderService {

    private final VaccineRepository vaccineRepository;

    public VaccineReminderService(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    public String getVaccineReminder(Dog dog) {
        // Get all vaccines for the dog
        List<Vaccine> vaccines = vaccineRepository.findByDogId(dog.getId());

        // If the dog is older than 1 year and has no vaccines at all
        if (dog.getAge() > 1 && vaccines.isEmpty()) {
            return "If your dog has not been vaccinated yet, please schedule the annual vaccine as soon as possible to ensure your dog’s health and well-being.";
        }

        // Check for the most recent puppy 1st round vaccine
        Vaccine firstPuppyVaccine = vaccines.stream()
                .filter(vaccine -> vaccine.getVaccineName() == VaccineName.PUPPY_1ST_ROUND)
                .max((v1, v2) -> v1.getVaccinationDate().compareTo(v2.getVaccinationDate()))
                .orElse(null);

        // Check for the most recent puppy 2nd round vaccine
        Vaccine secondPuppyVaccine = vaccines.stream()
                .filter(vaccine -> vaccine.getVaccineName() == VaccineName.PUPPY_2ND_ROUND)
                .max((v1, v2) -> v1.getVaccinationDate().compareTo(v2.getVaccinationDate()))
                .orElse(null);

        // Check for the most recent annual vaccine (for adult dogs)
        Vaccine recentAnnualVaccine = vaccines.stream()
                .filter(vaccine -> vaccine.getVaccineName() == VaccineName.ANNUAL_VACCINE)
                .max((v1, v2) -> v1.getVaccinationDate().compareTo(v2.getVaccinationDate()))
                .orElse(null);

        // If puppy 1st round vaccine was given more than a year ago and no annual vaccine is logged
        if (firstPuppyVaccine != null && ChronoUnit.YEARS.between(firstPuppyVaccine.getVaccinationDate(), LocalDate.now()) > 1 && recentAnnualVaccine == null) {
            return "It has been over a year since your dog received the 1st puppy vaccine. Please vaccinate your dog with the annual vaccine as soon as possible.";
        }

        // If puppy 1st and 2nd round vaccines are logged but no annual vaccine
        if (firstPuppyVaccine != null && secondPuppyVaccine != null && recentAnnualVaccine == null) {
            // Check if both puppy vaccines are older than 2 years
            LocalDate nextAnnualVaccineDate = secondPuppyVaccine.getVaccinationDate().plusYears(1);
            if (ChronoUnit.YEARS.between(firstPuppyVaccine.getVaccinationDate(), LocalDate.now()) > 2 &&
                    ChronoUnit.YEARS.between(secondPuppyVaccine.getVaccinationDate(), LocalDate.now()) > 2) {
                return "It is time to vaccinate your dog again with the annual vaccine. Please consult with your veterinarian to schedule an appointment for annual vaccine as soon as possible.";
            } else {
                return "Your dog is due for the annual vaccine by " + nextAnnualVaccineDate + ". Please contact your veterinarian to schedule the vaccine appointment.";
            }
        }

        // If there is a recent annual vaccine, set reminder for the next annual vaccine
        if (recentAnnualVaccine != null) {
            LocalDate nextAnnualVaccineDate = recentAnnualVaccine.getVaccinationDate().plusYears(1);
            if (nextAnnualVaccineDate.isBefore(LocalDate.now())) {
                return "It has been over a year since your dog's last annual vaccine. Please consult with your veterinarian to schedule an appointment for annual vaccine as soon as possible.";
            } else {
                return "Your dog is due for the annual vaccine by " + nextAnnualVaccineDate + ". Please contact your veterinarian to schedule the vaccine appointment.";
            }
        }

        // Default message
        return "Please check your dog's vaccination status and ensure they are up-to-date.";
    }
}
