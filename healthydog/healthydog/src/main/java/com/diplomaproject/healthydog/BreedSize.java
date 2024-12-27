package com.diplomaproject.healthydog;

import jakarta.persistence.*;

@Entity
@Table(name = "breed_size")
public class BreedSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size_name")
    private String sizeName;

    public BreedSize() {}

    public BreedSize(String sizeName) {
        this.sizeName = sizeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    //for testing purposes, to verify if correct data is printed in the console
    @Override
    public String toString() {
        return this.sizeName;
    }

}

