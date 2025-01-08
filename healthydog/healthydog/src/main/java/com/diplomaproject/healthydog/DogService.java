package com.diplomaproject.healthydog;
import jakarta.transaction.Transactional;
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

    @Autowired
    private BreedSizeRepository breedSizeRepository;

    public List<BreedsDataEntity> getAllBreeds() {
        return breedsRepository.findAll();
    }

    // Find dogs by user
    public List<Dog> findByUser(User user) {
        return dogRepository.findByUser(user);
    }

    // Find a dog by its ID
    public Dog findById(Long dogId) {
        Dog dog = dogRepository.findById(dogId).orElse(null);
        if (dog != null) {
            // Ensure the ageGroup is set correctly when retrieving the dog
            dog.assignAgeGroup();  // Trigger the method manually
        }
        return dog;
    }

    // Add a dog directly using the Dog entity
    public Dog addDog(Dog dog, User user) {
        dog.setUser(user);  // Associate the dog with the user
        return dogRepository.save(dog);  // Save the dog
    }



    public void saveDog(Dog dog) {
        if (dog.getBreedSize() != null) {
            breedSizeRepository.save(dog.getBreedSize()); // Ensure breed size is saved if needed
        }
        dogRepository.save(dog); // Save the dog entity
    }

    public void deleteDog(Long dogId) {
        if (dogRepository.existsById(dogId)) {
            dogRepository.deleteById(dogId);
        } else {
            throw new RuntimeException("Dog not found");
        }
    }

    //to calculate dog walks

    public Double calculateDailyGoal(Dog dog) {
        BreedSize breedSize = dog.getBreedSize();  // Fetch breed size through Breed
        String ageGroup = dog.getAgeGroup();

        // Default to a minimum value if breedSize or ageGroup is unknown
        if (breedSize == null || ageGroup == null || breedSize.getSizeName() == null) {
            return 2.0;  // Minimum goal
        }

        String sizeName = breedSize.getSizeName().toUpperCase();  // Get the size name for comparison

        switch (sizeName) {
            case "LARGE":
                if (ageGroup.equalsIgnoreCase("PUPPY")) {
                    return 3.0;
                } else if (ageGroup.equalsIgnoreCase("ADULT")) {
                    return 5.0;
                } else {
                    return 4.0;//for seniors
                }
            case "MEDIUM":
                if (ageGroup.equalsIgnoreCase("PUPPY")) {
                    return 2.5;
                } else if (ageGroup.equalsIgnoreCase("ADULT")) {
                    return 3.5;
                } else {
                    return 3.0;
                }
            case "SMALL":
                if (ageGroup.equalsIgnoreCase("PUPPY")) {
                    return 2.0;
                } else if (ageGroup.equalsIgnoreCase("ADULT")) {
                    return 3.0;
                } else {
                    return 2.5;
                }
            default:
                return 2.0;  //for unknown sizes
        }
    }



}
