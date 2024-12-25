package com.diplomaproject.healthydog;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DogFoodRepository extends JpaRepository<DogFood, Long> {

    // Custom query method to find food by breed size and age group
    List<DogFood> findByBreedSizeAndAgeGroup(BreedSize breedSize, String ageGroup);
}
