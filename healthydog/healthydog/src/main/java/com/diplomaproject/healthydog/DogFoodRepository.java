package com.diplomaproject.healthydog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogFoodRepository extends JpaRepository<DogFood, Long> {
    List<DogFood> findBySizeCategoryAndAgeCategory(String sizeCategory, String ageCategory);
}

