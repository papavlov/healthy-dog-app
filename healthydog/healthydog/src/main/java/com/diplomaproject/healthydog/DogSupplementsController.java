package com.diplomaproject.healthydog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplements")
public class DogSupplementsController {

    private final DogSupplementsService supplementsService;

    public DogSupplementsController(DogSupplementsService supplementsService) {
        this.supplementsService = supplementsService;
    }

    @GetMapping("/recommend")
    public ResponseEntity<List<DogSupplements>> recommendSupplements(
            @RequestParam Long breedSizeId,
            @RequestParam String ageGroup) {

        BreedSize breedSize = new BreedSize();
        breedSize.setId(breedSizeId);  // Create a temporary BreedSize with the given ID

        List<DogSupplements> supplements = supplementsService.getSupplementsForDog(breedSize, ageGroup);
        return ResponseEntity.ok(supplements);
    }
}

