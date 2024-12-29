package com.diplomaproject.healthydog;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

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
    private BreedsRepository breedsRepository;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private DogImageService dogImageService;

    private static final String UPLOAD_DIR = "uploads/";

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
    public String addDog(@ModelAttribute("dog") Dog dog,
                         @RequestParam("dogImage") MultipartFile file,
                         Authentication authentication,
                         Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        try {
            String username = authentication.getName();
            User user = userService.findByEmail(username);
            if (user == null) {
                model.addAttribute("error", "User not found. Please log in again.");
                return "add_dog";
            }
            dog.setUser(user);

            // Fetch the breed and set the breed size
            BreedsDataEntity selectedBreed = breedsRepository.findById(dog.getBreed().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Breed not found"));
            dog.setBreedSize(selectedBreed.getBreedSize());

            // Assign the appropriate age group based on dog’s age
            dog.assignAgeGroup();

            // Handle image upload (if file is provided)
            if (!file.isEmpty()) {
                // Resize and save the image
                String fileName = saveResizedImage(file);
                // Set the image URL for the dog object
                dog.setImageUrl("uploads/" + fileName);  // Store relative path
            }

            // Save the dog entity with all attributes including the image URL
            dogService.saveDog(dog);

            // Redirect to the dog list page after saving the dog
            return "redirect:/dogs/list";
        } catch (IOException e) {
            model.addAttribute("error", "Failed to save the dog image. Please try again.");
            return "add_dog";
        }
    }

    // Method to resize the uploaded image and save it
    private String saveResizedImage(MultipartFile image) throws IOException {
        // Ensure the file has a unique name
        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        Path uploadPath = Paths.get(UPLOAD_DIR);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);  // Create the uploads directory if it doesnt exist
        }

        // Create the final file path
        Path targetLocation = uploadPath.resolve(fileName);

        // Resize the image to a fixed size (300x300)
        Thumbnails.of(image.getInputStream())
                .size(300, 300)  // Set fixed size (width, height)
                .toFile(targetLocation.toFile());

        return fileName;
    }

    // For changing dog image
    @PostMapping("/{id}/changeImage")
    public String changeImage(@PathVariable("id") Long dogId, @RequestParam("image") MultipartFile image) {
        // Handle the file upload and update the dog's image URL
        try {
            String imageName = saveResizedImage(image);

            // Update the dog's record in the database
            Dog dog = dogService.findById(dogId);
            dog.setImageUrl("uploads/" + imageName);
            dogService.saveDog(dog);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error properly (e.g., show a message to the user)
        }

        return "redirect:/dogs/list"; // Redirect back to the dog list
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

        dogs.sort(Comparator.comparing(Dog::getId));
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

    // Delete a dog by ID
    @GetMapping("/delete/{dogId}")
    public String deleteDog(@PathVariable Long dogId, RedirectAttributes redirectAttributes) {
        Dog dog = dogRepository.findById(dogId).orElse(null);

        if (dog != null) {
            dogRepository.delete(dog); // Delete the dog from the repository
            redirectAttributes.addFlashAttribute("message", "Dog has been deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Dog not found!");
        }

        return "redirect:/dogs/list"; // Redirect to the list of dogs after deletion
    }
}
