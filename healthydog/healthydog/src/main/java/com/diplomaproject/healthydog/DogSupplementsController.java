package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller  // Use @Controller to render HTML views
@RequestMapping("/api/dogsupplements")
public class DogSupplementsController {

    private final DogSupplementsService dogSupplementsService;
    private final DogService dogService;

    @Autowired
    public DogSupplementsController(DogSupplementsService dogSupplementsService, DogService dogService) {
        this.dogSupplementsService = dogSupplementsService;
        this.dogService = dogService;
    }

    @GetMapping("/recommendations/{dogId}")
    public String showRecommendations(@PathVariable Long dogId, Model model) {
        //Fetch the Dog entity
        Dog dog = dogService.findById(dogId);
        BreedSize breedSize = dog.getBreedSize(); //Get the BreedSize object
        String ageGroup = dog.getAgeGroup();  //Get the age group as a String

        //dog details for debugging
        System.out.println("Dog details: " + dog);
        System.out.println("Breed size: " + breedSize);
        System.out.println("Age group: " + ageGroup);

        //Fetch supplement recommendations based on breed size and age group
        List<DogSupplements> recommendations = dogSupplementsService.getSupplementsForDog(breedSize, ageGroup);

        //Add attributes to the model to pass to the Thymeleaf template
        model.addAttribute("dog", dog);
        model.addAttribute("supplementRecommendations", recommendations);

        return "dog_supplements_recommendations";
    }

    //Endpoint for fetching dog supplement recommendations as a JSON response
    @GetMapping("/api/recommendations")
    public ResponseEntity<List<DogSupplements>> getRecommendations(@RequestParam BreedSize breedSize, @RequestParam String ageGroup) {
        List<DogSupplements> recommendations = dogSupplementsService.getSupplementsForDog(breedSize, ageGroup);
        return ResponseEntity.ok(recommendations);
    }
}
