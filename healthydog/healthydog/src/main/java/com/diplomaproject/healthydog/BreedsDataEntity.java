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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "breed_size_id", referencedColumnName = "id")
    private BreedSize breedSize;


    // Constructors
    public BreedsDataEntity() {}

    public BreedsDataEntity(String breedName, BreedSize breedSize) {

        this.breedName = breedName;
        this.breedSize = breedSize;
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

    public BreedSize getBreedSize() {
        return breedSize;
    }

    public void setBreedSize(BreedSize breedSize) {
        this.breedSize = breedSize;
    }
}