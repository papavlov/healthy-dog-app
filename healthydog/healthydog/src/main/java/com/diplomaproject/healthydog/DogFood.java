package com.diplomaproject.healthydog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DogFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;            // "Puppy Large Breed Food"
    private String sizeCategory;    // small, medium, large
    private String ageCategory;     // puppy, adult, senior
    private String description;     // additional info about the food

    public DogFood() {}

    public DogFood(Long id, String name, String sizeCategory, String description, String ageCategory) {
        this.id = id;
        this.name = name;
        this.sizeCategory = sizeCategory;
        this.description = description;
        this.ageCategory = ageCategory;
    }

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

    public String getSizeCategory() {
        return sizeCategory;
    }

    public void setSizeCategory(String sizeCategory) {
        this.sizeCategory = sizeCategory;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
