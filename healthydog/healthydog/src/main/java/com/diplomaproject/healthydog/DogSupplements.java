package com.diplomaproject.healthydog;

import jakarta.persistence.*;

@Entity
@Table(name = "dog_supplements")
public class DogSupplements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;            //Joint Support Supplement

    @ManyToOne(cascade = CascadeType.PERSIST) // Relationship with BreedSize entity
    private BreedSize breedSize;    // Relationship with BreedSize entity

    private String ageGroup;

    private String description;     // Additional info about the supplement

    public DogSupplements() {}

    public DogSupplements(Long id, String name, BreedSize breedSize, String ageGroup, String description) {
        this.id = id;
        this.name = name;
        this.breedSize = breedSize;
        this.ageGroup = ageGroup;
        this.description = description;
    }

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BreedSize getBreedSize() {
        return breedSize;
    }

    public void setBreedSize(BreedSize breedSize) {
        this.breedSize = breedSize;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

