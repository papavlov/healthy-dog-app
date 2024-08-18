package com.diplomaproject.healthydog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedsRepository extends JpaRepository<BreedsDataEntity, Long> {
}