package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dogs/{dogId}/collars")
public class DogCollarController {

    private final DogCollarService dogCollarService;
    private final DogService dogService;

    @Autowired
    public DogCollarController(DogCollarService dogCollarService, DogService dogService) {
        this.dogCollarService = dogCollarService;
        this.dogService = dogService;
    }

    @GetMapping
    public String showCollarRecommendations(@PathVariable Long dogId, Model model) {
        Dog dog = dogService.findById(dogId);  //dog entity
        BreedSize breedSize = dog.getBreedSize();  // fetch BreedSize from Dog entity
        System.out.println("BreedSize for " + dog.getName() + ": " + breedSize);  //check breed size

        //get recommended collars based on breed size
        List<DogCollar> recommendedCollars = dogCollarService.getRecommendedCollars(breedSize);
        System.out.println("Recommended collars: " + recommendedCollars);  //check therecommended collars

        if (recommendedCollars.isEmpty()) {
            model.addAttribute("message", "No recommendations available.");
        } else {
            model.addAttribute("dog", dog);
            model.addAttribute("recommendedCollars", recommendedCollars);
        }

        return "dog_collar_recommendations";
    }

}

