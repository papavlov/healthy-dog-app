package com.diplomaproject.healthydog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedSizeRepository extends JpaRepository<BreedSize, Long> {
    BreedSize findBySizeName(String sizeName);
}
