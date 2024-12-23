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

    // Find dogs by user
    public List<Dog> findByUser(User user) {
        return dogRepository.findByUser(user);
    }

    // Find a dog by its ID
    public Dog findById(Long id) {
        return dogRepository.findById(id).orElse(null); // or handle exception
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
