package com.diplomaproject.healthydog;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.diplomaproject.healthydog.BreedsDataInput;

import java.awt.*;
import java.io.FileNotFoundException;

@RestController
public interface BreedsDataController {
    @PostMapping(value = "/readBreedsData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void insertBreedsData(@RequestBody BreedsDataInput breedsdatainput) throws FileNotFoundException;
}
