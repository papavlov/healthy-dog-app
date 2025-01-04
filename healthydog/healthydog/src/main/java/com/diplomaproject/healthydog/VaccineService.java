package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private DogRepository dogRepository;

    public Vaccine addVaccine(Long dogId, VaccineName vaccineName, LocalDate vaccinationDate) {
        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(() -> new RuntimeException("Dog not found"));

        Vaccine vaccine = new Vaccine();
        vaccine.setVaccineName(vaccineName);  // Use enum instead of string
        vaccine.setVaccinationDate(vaccinationDate);
        vaccine.setDog(dog);

        return vaccineRepository.save(vaccine);
    }

    public List<Vaccine> getVaccinesForDog(Long dogId) {
        return vaccineRepository.findByDogId(dogId);
    }
    public void deleteVaccine(Long id) {
        vaccineRepository.deleteById(id);  // Delete the walk by ID
    }

}
