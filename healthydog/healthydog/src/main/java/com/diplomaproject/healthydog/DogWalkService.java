package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class DogWalkService {

    @Autowired
    private DogWalkRepository dogWalkRepository;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private DogService dogService;


    public void logWalk(Long dogId, Double distance, LocalDate walkDate, Integer duration) {
        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(() -> new RuntimeException("Dog not found"));

        DogWalk walk = new DogWalk();
        walk.setDog(dog);
        walk.setDistance(distance);
        walk.setWalkDate(walkDate);
        walk.setDuration(duration);
        dogWalkRepository.save(walk);
    }

    public Double getDailyWalkSum(Long dogId, LocalDate date) {
        return dogWalkRepository.findByDogIdAndWalkDate(dogId, date)
                .stream()
                .mapToDouble(DogWalk::getDistance)
                .sum();
    }

    public Double getMonthlyTotal(Long dogId, YearMonth yearMonth) {
        LocalDate start = yearMonth.atDay(1);
        LocalDate end = yearMonth.atEndOfMonth();
        return dogWalkRepository.findByDogIdAndWalkDateBetween(dogId, start, end)
                .stream()
                .mapToDouble(DogWalk::getDistance)
                .sum();
    }

    public List<DogWalk> findWalksByDogId(Long dogId) {
        List<DogWalk> walks = dogWalkRepository.findByDogId(dogId);
        for (DogWalk walk : walks) {
            walk.getDailyGoal(dogService);
        }
        return walks;
    }

    public List<DogWalk> findWalksByDogIdAndDate(Long dogId, LocalDate walkDate) {
        return dogWalkRepository.findByDogIdAndWalkDate(dogId, walkDate);
    }



    public void deleteWalk(Long id) {
        dogWalkRepository.deleteById(id);  // Delete the walk by ID
    }
    public List<DogWalk> getAllWalks() {
        return dogWalkRepository.findAll();  // Retrieve all walks
    }
}

