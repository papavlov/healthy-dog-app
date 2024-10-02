package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ApplicationController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }

    /*
        @GetMapping("/index")
    public String print() {
        return "Welcome to the index page";

    }
     */
    //registration
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "registration_page";
    }
    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "registration_successful";
    }
    @GetMapping("/home_page")
    public String homePage() {
        return "home_page"; // Assuming you have "home_page.html" in your templates folder
    }
    @GetMapping("/add_dog")
    public String addDog(Model model) {
        model.addAttribute("dog", new Dog());

        return "add_dog";
    }

    @GetMapping("/see_your_dogs")
    public String seeYourDogs(Model model) {
        // Retrieve dog information from the database
        //model.addAttribute("dogs", dogService.findAllDogs()); // Example service method
        return "see_your_dogs"; // The name of the view (Thymeleaf template)
    }
}