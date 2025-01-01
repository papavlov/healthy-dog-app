package com.diplomaproject.healthydog;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dog_walks")
public class DogWalk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dog_id", nullable = false)
    private Dog dog;

    @Column(name = "walk_date", nullable = false)
    private LocalDate walkDate;

    @Column(name = "distance", nullable = false)
    private Double distance;
    @Column(name = "duration", nullable = false)
    private Integer duration; // duration in minutes

    @Transient
    private Double dailyGoal;


    public DogWalk() {}

    public DogWalk(Dog dog, LocalDate walkDate, Double distance, Integer duration) {
        this.dog = dog;
        this.walkDate = walkDate;
        this.distance = distance;
        this.duration = duration;
    }

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public LocalDate getWalkDate() {
        return walkDate;
    }

    public void setWalkDate(LocalDate walkDate) {
        this.walkDate = walkDate;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getDailyGoal(DogService dogService) {
        if (dailyGoal == null) {
            dailyGoal = dogService.calculateDailyGoal(this.getDog());
        }
        return dailyGoal;
    }
}
