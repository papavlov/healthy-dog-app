package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dog-food")
public class DogFoodController {
    @Autowired
    private DogFoodService dogFoodService;

    @GetMapping("/recommend")
    public ResponseEntity<List<DogFood>> getRecommendations(
            @RequestParam String size,
            @RequestParam String age) {
        List<DogFood> recommendations = dogFoodService.recommendFood(size, age);
        return ResponseEntity.ok(recommendations);
    }
}

