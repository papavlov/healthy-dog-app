package com.diplomaproject.healthydog;

import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BreedsService {

    @Autowired
    private BreedsRepository dogBreedRepository;

    @Autowired
    private BreedSizeRepository breedSizeRepository;

    @Autowired
    private EntityManager entityManager; // Add entity manager to manage entity states

    @Transactional
    public void saveDogBreedsFromCsv(String fileName) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(new ClassPathResource(fileName).getInputStream()))) {
            String[] nextLine;
            List<BreedsDataEntity> dogBreeds = new ArrayList<>();

            // Skip the header
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                Long breedId = Long.parseLong(nextLine[0]);
                String breedName = nextLine[1];

                // Determine breed size dynamically
                BreedSize breedSize = determineSizeByBreed(breedName);

                // If breed size is detached, merge it into the persistence context
                if (breedSize != null && breedSize.getId() != null) {
                    breedSize = entityManager.merge(breedSize);
                }

                // Create and add the breed entity
                dogBreeds.add(new BreedsDataEntity(breedName, breedSize));
            }

            // Save all to database
            dogBreedRepository.saveAll(dogBreeds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Map breeds to sizes dynamically
    private BreedSize determineSizeByBreed(String breedName) {
        // Fetch sizes from the database and ensure they are managed
        BreedSize small = breedSizeRepository.findBySizeName("SMALL");
        BreedSize medium = breedSizeRepository.findBySizeName("MEDIUM");
        BreedSize large = breedSizeRepository.findBySizeName("LARGE");

        if (breedName == null || breedName.isEmpty()) {
            return medium; // Default to medium or handle as needed
        }

        // Define mappings using the objects fetched from the database
        Map<String, BreedSize> breedSizeMap = new HashMap<>();
        breedSizeMap.put("CHIHUAHUA", small);
        breedSizeMap.put("YORKSHIRE TERRIER", small);
        breedSizeMap.put("DACHSHUND", small);
        breedSizeMap.put("MALTESE", small);
        breedSizeMap.put("PEKINGESE", small);
        breedSizeMap.put("SHIH TZU", small);
        breedSizeMap.put("POMERANIAN", small);
        breedSizeMap.put("TOY POODLE", small);
        breedSizeMap.put("JACK RUSSELL TERRIER", small);
        breedSizeMap.put("PAPILLON", small);
        breedSizeMap.put("FRENCH BULLDOG", small);
        breedSizeMap.put("PUG", small);
        breedSizeMap.put("MINIATURE PINSCHER", small);
        breedSizeMap.put("BOSTON TERRIER", small);
        breedSizeMap.put("BICHON FRISE", small);
        breedSizeMap.put("ITALIAN GREYHOUND", small);
        breedSizeMap.put("CAVALIER KING CHARLES SPANIEL", small);
        breedSizeMap.put("MINIATURE SCHNAUZER", small);

        breedSizeMap.put("BORDER COLLIE", medium);
        breedSizeMap.put("BULLDOG", medium);
        breedSizeMap.put("COCKER SPANIEL", medium);
        breedSizeMap.put("STAFFORDSHIRE BULL TERRIER", medium);
        breedSizeMap.put("AUSTRALIAN SHEPHERD", medium);
        breedSizeMap.put("BASSET HOUND", medium);
        breedSizeMap.put("STANDARD SCHNAUZER", medium);
        breedSizeMap.put("VIZSLA", medium);
        breedSizeMap.put("WHIPPET", medium);
        breedSizeMap.put("AMERICAN STAFFORDSHIRE TERRIER", medium);
        breedSizeMap.put("PORTUGUESE WATER DOG", medium);
        breedSizeMap.put("KEESHOND", medium);
        breedSizeMap.put("SAMOYED", medium);
        breedSizeMap.put("ENGLISH SPRINGER SPANIEL", medium);

        breedSizeMap.put("GOLDEN RETRIEVER", large);
        breedSizeMap.put("LABRADOR RETRIEVER", large);
        breedSizeMap.put("GERMAN SHEPHERD DOG", large);
        breedSizeMap.put("ROTTWEILER", large);
        breedSizeMap.put("BOXER", large);
        breedSizeMap.put("DOBERMANN", large);
        breedSizeMap.put("GREAT DANE", large);
        breedSizeMap.put("SIBERIAN HUSKY", large);
        breedSizeMap.put("ALASKAN MALAMUTE", large);
        breedSizeMap.put("BERNESE MOUNTAIN DOG", large);
        breedSizeMap.put("SAINT BERNARD", large);
        breedSizeMap.put("LEONBERGER", large);
        breedSizeMap.put("RHODESIAN RIDGEBACK", large);
        breedSizeMap.put("NEAPOLITAN MASTIFF", large);
        breedSizeMap.put("BULLMASTIFF", large);
        breedSizeMap.put("GREAT PYRENEES", large);
        breedSizeMap.put("IRISH WOLFHOUND", large);
        breedSizeMap.put("TIBETAN MASTIFF", large);
        breedSizeMap.put("KUVASZ", large);
        breedSizeMap.put("NEWFOUNDLAND", large);
        breedSizeMap.put("AKITA", large);
        breedSizeMap.put("DOGO ARGENTINO", large);

        // Fetch breed size from map or default to medium
        BreedSize breedSize = breedSizeMap.getOrDefault(breedName.toUpperCase(), medium);

        // Log the breed and size mapping
        if (breedSize != null) {
            System.out.println("Breed Name: " + breedName + " -> Breed Size: " + breedSize.getSizeName());
        } else {
            System.out.println("Breed Name: " + breedName + " -> Breed Size: Not Found");
        }

        return breedSize;
    }
}
