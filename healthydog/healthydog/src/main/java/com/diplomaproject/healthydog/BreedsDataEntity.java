package com.diplomaproject.healthydog;

import jakarta.persistence.*;

@Entity
@Table(name = "breeds_data")

public class BreedsDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "breed_name")
    private String breedName;

    // Constructors
    public BreedsDataEntity() {}

    public BreedsDataEntity(String breedName) {

        this.breedName = breedName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }
}