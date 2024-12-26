package com.diplomaproject.healthydog;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DogSupplementsService {

    private final DogSupplementsRepository supplementsRepository;
    private DogRepository dogRepository;

    public DogSupplementsService(DogSupplementsRepository supplementRepository) {
        this.supplementsRepository = supplementRepository;
    }

    public List<DogSupplements> getSupplementsForDog(BreedSize breedSize, String ageGroup) {
        return supplementsRepository.findByBreedSizeAndAgeGroup(breedSize, ageGroup);
    }

    public Dog getDogDetails(Long dogId) {
        return dogRepository.findById(dogId).orElseThrow(() -> new RuntimeException("Dog not found"));
    }

}

