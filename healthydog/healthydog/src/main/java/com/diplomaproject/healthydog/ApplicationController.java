package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApplicationController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }

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

}