package com.diplomaproject.healthydog;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DogFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;            //"Puppy Large Breed Food"

    @ManyToOne(cascade = CascadeType.PERSIST) //Ensure BreedSize is saved if new
    private BreedSize breedSize;    //Relationship with BreedSize entity

    private String ageGroup;        //alternative - enum

    private String description;     //Additional info about the food


    public DogFood() {}

    // Constructor for manual creation
    public DogFood(Long id, String name, BreedSize breedSize, String description, String ageGroup) {
        this.id = id;
        this.name = name;
        this.breedSize = breedSize;
        this.description = description;
        this.ageGroup = ageGroup;
    }

    // Getters and setters
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
