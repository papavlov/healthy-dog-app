package com.diplomaproject.healthydog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DogService {

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private UserRepository userRepository;  // Assuming you have a User repository

    @Autowired
    private BreedsRepository breedsRepository;

    public List<BreedsDataEntity> getAllBreeds() {
        return breedsRepository.findAll();
    }

    // Find dogs by user
    public List<Dog> findByUser(User user) {
        return dogRepository.findByUser(user);
    }

    // Find a dog by its ID
    public Dog findById(Long dogId) {
        return dogRepository.findById(dogId).orElseThrow(() -> new RuntimeException("Dog not found"));
    }

    // Add a dog directly using the Dog entity
    public Dog addDog(Dog dog, User user) {
        dog.setUser(user);  // Associate the dog with the user
        return dogRepository.save(dog);  // Save the dog
    }

    public void saveDog(Dog dog) {
        dogRepository.save(dog);
    }
}
