package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogFoodService {
    @Autowired
    private DogFoodRepository dogFoodRepository;

    public List<DogFood> recommendFood(String sizeCategory, String ageCategory) {
        return dogFoodRepository.findBySizeCategoryAndAgeCategory(sizeCategory, ageCategory);
    }
}

