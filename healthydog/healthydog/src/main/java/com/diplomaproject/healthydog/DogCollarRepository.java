package com.diplomaproject.healthydog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogCollarRepository extends JpaRepository<DogCollar, Long> {
    //List<DogCollar> findByBreedSize(BreedSize breedSize);

    @Query("SELECT c FROM DogCollar c JOIN FETCH c.breedSize WHERE c.breedSize = :breedSize")
    List<DogCollar> findByBreedSizeWithBreed(@Param("breedSize") BreedSize breedSize);

}


