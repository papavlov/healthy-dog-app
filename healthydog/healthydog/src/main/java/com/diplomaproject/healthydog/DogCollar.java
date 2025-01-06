package com.diplomaproject.healthydog;

import jakarta.persistence.*;

@Entity
@Table(name = "dog_collars")
public class DogCollar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String collarName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_size_id", nullable = false)  // Foreign key to BreedSize
    private BreedSize breedSize;

    private String description;

    public DogCollar() {}

    public DogCollar(Long id, String collarName, BreedSize breedSize, String description) {
        this.id = id;
        this.collarName = collarName;
        this.breedSize = breedSize;
        this.description = description;
    }

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollarName() {
        return collarName;
    }

    public void setCollarName(String collarName) {
        this.collarName = collarName;
    }

    public BreedSize getBreedSize() {
        return breedSize;
    }

    public void setBreedSize(BreedSize breedSize) {
        this.breedSize = breedSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //for testing purposes, to verify if correct data is printed in the console
    @Override
    public String toString() {
        return "DogCollar{name='" + collarName + "', size=" + breedSize + "}";
    }
}

