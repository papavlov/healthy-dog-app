package com.diplomaproject.healthydog;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "breeds_data")
public class BreedsDataEntity {

    @Column(name = "breed_id")
    @Id
    private Integer breed_id;

    @Column(name = "breed_name")
    private String breed_name;

    public BreedsDataEntity(Integer breed_id, String breed_name) {
    }

    public BreedsDataEntity() {

    }

    public Integer getBreedId() {
        return breed_id;
    }

    public void setBreedId(Integer breed_id) {
        this.breed_id = breed_id;
    }

    public String getBreedName() {
        return breed_name;
    }

    public void setBreedName(String breed_name) {
        this.breed_name = breed_name;
    }
}