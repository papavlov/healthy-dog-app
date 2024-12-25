package com.diplomaproject.healthydog;

import jakarta.persistence.*;

@Entity
@Table(name = "breed_size")
public class BreedSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(unique = true, nullable = false)
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
}

