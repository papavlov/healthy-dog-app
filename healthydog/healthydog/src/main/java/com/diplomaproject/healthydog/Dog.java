package com.diplomaproject.healthydog;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "breed_id")
    private BreedsDataEntity breed;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "breed_size_id", referencedColumnName = "id")
    private BreedSize breedSize;


    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "age_group")
    private String ageGroup;

    @Column(name = "weight", nullable = false)
    private Float weight;

    @ManyToOne
    @JoinColumn(name = "user_id") // This is the foreign key column
    private User user;

    // One-to-many relationship with the Vaccine entity
    @OneToMany(mappedBy = "dog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vaccine> vaccines = new ArrayList<>();

    // Getters and setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public BreedsDataEntity getBreed() {
        return breed;
    }

    public void setBreed(BreedsDataEntity breed) {
        this.breed = breed;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
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

    // Getters and setters for vaccines

    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    public void addVaccine(Vaccine vaccine) {
        vaccines.add(vaccine);
        vaccine.setDog(this);  // Set the dog's reference in the vaccine
    }

    public void removeVaccine(Vaccine vaccine) {
        vaccines.remove(vaccine);
        vaccine.setDog(null);  // Remove the dog's reference in the vaccine
    }

    //methods to determine the ageGroup of a dog

    @PrePersist
    @PreUpdate
    public void assignAgeGroup() {
        this.ageGroup = determineAgeGroup(this.age);
    }

    private String determineAgeGroup(Integer age) {
        if (age == null) {
            return "UNKNOWN";  // Fallback if age is null
        }
        if (age <= 1) {
            return "PUPPY";
        } else if (age <= 7) {
            return "ADULT";
        } else {
            return "SENIOR";
        }
    }
}
