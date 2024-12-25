package com.diplomaproject.healthydog;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BreedSizeService {

    @Autowired
    private BreedSizeRepository breedSizeRepository;

    @PostConstruct
    public void populateBreedSizes() {
        if (breedSizeRepository.findBySizeName("SMALL") == null) {
            breedSizeRepository.save(new BreedSize("SMALL"));
        }
        if (breedSizeRepository.findBySizeName("MEDIUM") == null) {
            breedSizeRepository.save(new BreedSize("MEDIUM"));
        }
        if (breedSizeRepository.findBySizeName("LARGE") == null) {
            breedSizeRepository.save(new BreedSize("LARGE"));
        }
    }
}

