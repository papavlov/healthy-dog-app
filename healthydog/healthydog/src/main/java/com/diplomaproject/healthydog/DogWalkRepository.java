package com.diplomaproject.healthydog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DogWalkRepository extends JpaRepository<DogWalk, Long> {
    // Custom query to fetch walks by dog ID
    List<DogWalk> findByDogId(Long dogId);
    List<DogWalk> findByDogIdAndWalkDate(Long dogId, LocalDate walkDate);
    List<DogWalk> findByDogIdAndWalkDateBetween(Long dogId, LocalDate start, LocalDate end);

}
