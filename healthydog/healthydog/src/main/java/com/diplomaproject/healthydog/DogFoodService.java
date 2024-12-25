package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogFoodService {
    private final DogFoodRepository dogFoodRepository;

    // Injecting the DogFoodRepository
    public DogFoodService(DogFoodRepository dogFoodRepository) {
        this.dogFoodRepository = dogFoodRepository;
    }

    // Method to get food recommendations based on breed size and age group
    public List<DogFood> getRecommendations(BreedSize breedSize, String ageGroup) {
        System.out.println("Fetching dog food recommendations for breed size: " + breedSize + ", age group: " + ageGroup);
        return dogFoodRepository.findByBreedSizeAndAgeGroup(breedSize, ageGroup);
    }




}
