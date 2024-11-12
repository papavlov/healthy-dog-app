package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dogs")
public class DogController {
    @Autowired
    private DogService dogService;

    //@PreAuthorize("hasRole('USER')")
    @PostMapping("/add")
    public String addDog(@ModelAttribute Dog dog, Model model) {
        dogService.addDog(dog);
        model.addAttribute("message", "Dog added successfully!");
        return "dogForm";  // redirect to a page where the dog is shown
    }
}

/*
@RestController
@RequestMapping("/api/dogs")
public class DogController {

    private final DogService dogService;

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @PostMapping("/add")
    public Dog addDog(@RequestBody Dog dog, @AuthenticationPrincipal UserDetails userDetails) {
        // Use the authenticated user's details if necessary
        return dogService.saveDog(dog);
    }

}
*/
