package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BreedsDataInitializer implements CommandLineRunner {

    @Autowired
    private BreedsService breedsService;

    @Override
    public void run(String... args) throws Exception {
        // Specify the path to the CSV file
        String filePath = "breeds.csv"; // Adjust this to your file's path

        // Automatically upload the breeds data
        breedsService.saveDogBreedsFromCsv(filePath);

        System.out.println("Breeds uploaded successfully during application startup.");
    }
}
