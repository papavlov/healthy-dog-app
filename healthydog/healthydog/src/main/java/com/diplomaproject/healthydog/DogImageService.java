package com.diplomaproject.healthydog;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class DogImageService {

    private static final String UPLOAD_DIR = "uploads"; // Directory to store images, relative to the project root

    public String saveImage(MultipartFile file) throws IOException {
        // Generate a unique filename
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // Define the path for the file
        Path uploadPath = Paths.get(UPLOAD_DIR);  // Ensure this points to the root of your project

        // Ensure directory exists
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the file to the specified path
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        // Return the relative URL to the file
        return "/uploads/" + fileName;  // This URL should work after moving the folder
    }
}



