package com.diplomaproject.healthydog;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogCollarService {

    private final DogCollarRepository collarRepository;

    @Autowired
    public DogCollarService(DogCollarRepository collarRepository) {
        this.collarRepository = collarRepository;
    }

    @Transactional
    public List<DogCollar> getRecommendedCollars(BreedSize breedSize) {
        // Fetch both DogCollar and BreedSize in one query
        List<DogCollar> collars = collarRepository.findByBreedSizeWithBreed(breedSize);
        System.out.println("Collars for breed size " + breedSize + ": " + collars);
        return collars;
    }
}


