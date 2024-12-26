package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller  // Changed from @RestController to @Controller to render HTML views
@RequestMapping("/api/dogfood")
public class DogFoodController {

    private final DogFoodService dogFoodService;
    private final DogService dogService;

    @Autowired
    public DogFoodController(DogFoodService dogFoodService, DogService dogService) {
        this.dogFoodService = dogFoodService;
        this.dogService = dogService;
    }

    @GetMapping("/recommendations/{dogId}")
    public String showRecommendations(@PathVariable Long dogId, Model model) {
        Dog dog = dogService.findById(dogId);  //Fetch the Dog entity
        BreedSize breedSize = dog.getBreedSize(); //Fetch BreedSize object
        String ageGroup = dog.getAgeGroup();  //Get the age group as a String

        System.out.println("Dog details: " + dog);
        System.out.println("Breed size: " + breedSize);
        System.out.println("Age group: " + ageGroup);

        List<DogFood> recommendations = dogFoodService.getRecommendations(breedSize, ageGroup);

        model.addAttribute("dog", dog);
        model.addAttribute("recommendations", recommendations);

        return "dog_food_recommendations";
    }


    // Endpoint for fetching dog food recommendations as a JSON response
    @GetMapping("/api/recommendations")
    public ResponseEntity<List<DogFood>> getRecommendations(@RequestParam BreedSize breedSize, @RequestParam String ageGroup) {
        List<DogFood> recommendations = dogFoodService.getRecommendations(breedSize, ageGroup);
        return ResponseEntity.ok(recommendations);
    }
}
