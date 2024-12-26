package com.diplomaproject.healthydog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogSupplementsRepository extends JpaRepository<DogSupplements, Long> {
    List<DogSupplements> findByBreedSizeAndAgeGroup(BreedSize breedSize, String ageGroup);
}

