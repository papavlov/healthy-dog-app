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

    // Add this method to find dogs by user
    public List<Dog> findByUser(User user) {
        return dogRepository.findByUser(user);
    }

    // Add this method to find a dog by its ID
    public Dog findById(Long id) {
        return dogRepository.findById(id).orElse(null); // or handle exception if you prefer
    }


    public Dog addDog(DogDTO dogDTO, User user) {
        Dog dog = new Dog();
        dog.setName(dogDTO.getName());
        dog.setBreed(dogDTO.getBreed());
        dog.setAge(dogDTO.getAge());
        dog.setWeight(dogDTO.getWeight());
        dog.setUser(user);
        return dogRepository.save(dog);
    }

    public void saveDog(Dog dog) {
        dogRepository.save(dog);
    }
}
