package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dogs")
public class DogController {

    @Autowired
    private UserService userService;

    @Autowired
    private DogService dogService;

    @Autowired
    private VaccineService vaccineService;

    @Autowired
    private BreedsRepository breedsRepository; // Add the BreedsDataRepository to fetch breeds

    // Display the Add Dog form
    @GetMapping("/add_dog")
    public String addDog(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login"; // Redirect if not authenticated
        }

        List<BreedsDataEntity> breeds = breedsRepository.findAll(); // Fetch all breeds
        model.addAttribute("breeds", breeds); // Add breeds to the model
        model.addAttribute("dog", new Dog()); // Add empty Dog object for form binding
        return "add_dog"; // Return the add_dog.html view
    }

    // Handle form submission for adding a dog

    @PostMapping("/add_dog")
    public String addDog(@ModelAttribute("dog") Dog dog, Authentication authentication, Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login"; // Ensure user is logged in
        }
        try {
            String username = authentication.getName(); // Get logged-in user's username
            User user = userService.findByEmail(username); // Fetch user by email
            if (user == null) {
                model.addAttribute("error", "User not found. Please log in again.");
                return "add_dog"; // Return to form with error message
            }
            dog.setUser(user); // Associate the dog with the logged-in user

            // Automatically set the breed size based on the selected breed
            BreedsDataEntity selectedBreed = breedsRepository.findById(dog.getBreed().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Breed not found"));
            dog.setBreedSize(selectedBreed.getBreedSize()); // Set breed size from the selected breed

            dogService.saveDog(dog); // Save the dog
            return "redirect:/dogs/list"; // Redirect to the list of dogs
        } catch (Exception e) {
            model.addAttribute("error", "Failed to save the dog. Please try again.");
            return "add_dog"; // Return to the form with error message
        }
    }


    // List all dogs for the current user
    @GetMapping("/list")
    public String listDogs(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login"; // Redirect if not authenticated
        }

        String username = authentication.getName(); // Get logged-in user's username
        User user = userService.findByEmail(username); // Fetch user by email

        if (user == null) {
            model.addAttribute("error", "User not found. Please log in again.");
            return "login"; // Redirect to login page with error
        }

        // Fetch the list of dogs for the user
        List<Dog> dogs = dogService.findByUser(user);

        // Fetch vaccines for each dog and add them to the dog's object
        for (Dog dog : dogs) {
            List<Vaccine> vaccines = vaccineService.getVaccinesForDog(dog.getId()); // Retrieve vaccines for the dog
            dog.setVaccines(vaccines); // Set vaccines in the dog object
        }

        // Add the list of dogs (with vaccines) to the model
        model.addAttribute("dogs", dogs);

        return "dog_list"; // Return the dog_list.html view
    }

    // Show details for a specific dog
    @GetMapping("/{id}")
    public String showDog(@PathVariable Long id, Model model) {
        Dog dog = dogService.findById(id); // Fetch the dog by ID
        if (dog == null) {
            model.addAttribute("error", "Dog not found.");
            return "redirect:/dogs/list"; // Redirect to the list if not found
        }
        model.addAttribute("dog", dog); // Add the dog to the model
        return "dog_detail"; // Return the dog_detail.html view
    }
}
