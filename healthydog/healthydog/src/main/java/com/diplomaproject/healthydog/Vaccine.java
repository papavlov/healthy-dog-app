package com.diplomaproject.healthydog;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VaccineName vaccineName;
    private LocalDate vaccinationDate;

    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VaccineName getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(VaccineName vaccineName) {
        this.vaccineName = vaccineName;
    }

    public LocalDate getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(LocalDate vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
