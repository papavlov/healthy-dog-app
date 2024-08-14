package com.diplomaproject.healthydog;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Column;

public class CsvPojo {

    @CsvBindByName(column = "id")
    private Integer breedId;

    @CsvBindByName(column = "name")
    private String breedName;

    public Integer getBreedId() {
        return breedId;
    }

    public void setBreedId(Integer breedId) {
        this.breedId = breedId;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }
}
