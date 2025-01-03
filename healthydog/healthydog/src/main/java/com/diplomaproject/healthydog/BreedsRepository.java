package com.diplomaproject.healthydog;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BreedsRepository extends JpaRepository<BreedsDataEntity, Long> {
    // Custom method to find a breed by its name
    Optional<BreedsDataEntity> findByBreedName(String breedName);
}
