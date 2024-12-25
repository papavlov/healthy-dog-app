package com.diplomaproject.healthydog;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {
    List<Dog> findByUser(User user);

}
