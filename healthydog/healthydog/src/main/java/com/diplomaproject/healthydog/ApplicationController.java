package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApplicationController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordResetService passwordResetService;

    @Value("${app.url}")
    private String appUrl;

    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }
    // Custom Login Page
    @GetMapping("/login")
    public String loginPage() {
        return "login_page";
    }

    // Registration
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

    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "logout";  // Return the logout.html page
    }

    @GetMapping("/home_page")
    public String homePage() {
        return "home_page";
    }

    // Forgotten Password Logic
    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot_password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email) {
        boolean result = passwordResetService.sendPasswordResetEmail(email);
        if (result) {
            // Redirect to Spring Security's default login page, ensuring it's not treated as a custom page
            return "redirect:/login";
        } else {
            // Redirect back to the forgot-password page with an error message
            return "redirect:/forgot-password?error=email-not-found";
        }
    }


    // Reset Password Form
    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        User user = passwordResetService.isValidToken(token);

        if (user == null) {
            model.addAttribute("error", "Invalid or expired token.");
            return "error-page";
        }

        model.addAttribute("token", token);
        return "reset_password";
    }


    // Reset Password Logic
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("newPassword") String newPassword, @RequestParam("token") String token) {
        if (newPassword == null || newPassword.isEmpty()) {
            return "redirect:/reset-password?error=empty-password&token=" + token;
        }
        boolean success = passwordResetService.resetPassword(token, newPassword);
        if (success) {
            return "redirect:/login";
        }
        return "redirect:/reset-password?error=invalid-token";
    }
}
