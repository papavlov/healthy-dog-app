package com.diplomaproject.healthydog;

import jakarta.persistence.*;

@Entity
@Table(name = "breeds_data")

public class BreedsDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "breed_id")
    private Long breedId;

    @Column(name = "breed_name")
    private String breedName;

    // Constructors
    public BreedsDataEntity() {}

    public BreedsDataEntity(Long breedId, String breedName) {
        this.breedId = breedId;
        this.breedName = breedName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBreedId() {
        return breedId;
    }

    public void setBreedId(Long breedId) {
        this.breedId = breedId;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }
}