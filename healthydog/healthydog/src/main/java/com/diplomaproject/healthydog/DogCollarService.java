package com.diplomaproject.healthydog;

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

    public List<DogCollar> getRecommendedCollars(BreedSize breedSize) {
        List<DogCollar> collars = collarRepository.findByBreedSize(breedSize);
        System.out.println("Collars for breed size " + breedSize + ": " + collars);//test print to check if app fetches the correct collars for specific breed
        return collars;
    }


}


