package com.diplomaproject.healthydog;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class BreedsService {

    @Autowired
    private BreedsRepository dogBreedRepository;

    public void saveDogBreedsFromCsv(String fileName) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(new ClassPathResource(fileName).getInputStream()))) {
            String[] nextLine;
            List<BreedsDataEntity> dogBreeds = new ArrayList<>();

            // Skip the header
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                Long breedId = Long.parseLong(nextLine[0]);
                String breedName = nextLine[1];
                dogBreeds.add(new BreedsDataEntity(breedId, breedName));
            }

            dogBreedRepository.saveAll(dogBreeds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
