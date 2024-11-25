package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/add_dog")
    public String addDog(Model model) {
        model.addAttribute("dog", new Dog());
        return "add_dog";
    }



    @PostMapping("/add_dog")
    public String addDog(@ModelAttribute("dog") Dog dog, Authentication authentication) {
        String username = authentication.getName();  // Get logged in user's name
        User user = userService.findByEmail(username);  // Get the actual logged-in user
        dog.setUser(user);  // Associate the dog with the logged in user
        dogService.saveDog(dog);  // Save the dog through the service
        return "redirect:/";  // Redirect to the home page or dog list
    }



    // List all dogs for the current user
    @GetMapping("/list")
    public String listDogs(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByEmail("user@example.com");
        List<Dog> dogs = dogService.findByUser(user);  // Fetch the dogs for the user
        model.addAttribute("dogs", dogs);  // Add the list of dogs to the model
        return "dog_list";  // Display the list in a view
    }

    // Show a specific dog
    @GetMapping("/{id}")
    public String showDog(@PathVariable Long id, Model model) {
        Dog dog = dogService.findById(id);  // Get the dog by ID
        model.addAttribute("dog", dog);  // Add the dog to the model
        return "dog_detail";  // Display the dog details in a view
    }
}
