package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;

@RestController
public class BreedsDataController {

    @Autowired
    private BreedsService dogBreedService;

    @GetMapping("/upload-breeds")
    public String uploadBreeds(@RequestParam String filePath) {
        dogBreedService.saveDogBreedsFromCsv(filePath);
        return "Breeds uploaded successfully!";
    }

    //to trigger the GET query: http://localhost:8080/upload-breeds?filePath=breeds.csv

}