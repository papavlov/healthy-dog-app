package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService {

    @Autowired
    private DogRepository dogRepository;

    public Dog addDog(Dog dog) {
        return dogRepository.save(dog);
    }
    /*
    private final DogRepository dogRepository;

    @Autowired
    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    // Retrieve all dogs
    public List<Dog> findAllDogs() {
        return dogRepository.findAll();
    }

    // Retrieve a dog by ID
    public Optional<Dog> findDogById(Long id) {
        return dogRepository.findById(id);
    }

    // Save a new dog
    public Dog saveDog(Dog dog) {
        return dogRepository.save(dog);
    }

    // Update an existing dog
    public Dog updateDog(Long id, Dog dogDetails) {
        Dog dog = dogRepository.findById(id).orElseThrow(() -> new RuntimeException("Dog not found"));
        dog.setDogName(dogDetails.getDogName());
        dog.setBreed(dogDetails.getBreed());
        dog.setAge(dogDetails.getAge());
        dog.setWeight(dogDetails.getWeight());
        return dogRepository.save(dog);
    }

    // Delete a dog
    public void deleteDog(Long id) {
        dogRepository.deleteById(id);
    }
    */

}
