package com.diplomaproject.healthydog;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class BreedsDataService implements BreedsDataController {

    @Autowired
    BreedsDataRepository breedsDataRepository;

    @Override
    public void insertBreedsData(BreedsDataInput breedsdatainput) throws FileNotFoundException {
        List<CsvPojo> breedsDataEntities = new CsvToBeanBuilder(new FileReader(breedsdatainput.getPath())).
                withType(BreedsDataEntity.class).build().parse();

        BreedsDataEntity bde = new BreedsDataEntity();

        for (CsvPojo b : breedsDataEntities) {
            System.out.println(b.getBreedId() + "*****" + b.getBreedName());
            bde.setBreedId(b.getBreedId());
            bde.setBreedName(b.getBreedName());
            breedsDataRepository.save(bde);
            breedsDataRepository.flush();

            System.out.println("Saved the record ... " + b.getBreedId());
        }


    }
}
