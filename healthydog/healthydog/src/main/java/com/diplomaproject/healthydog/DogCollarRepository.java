package com.diplomaproject.healthydog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogCollarRepository extends JpaRepository<DogCollar, Long> {
    List<DogCollar> findByBreedSize(BreedSize breedSize);
}


