package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @Value("${app.resetPasswordUrl}")
    private String resetPasswordUrl;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean sendPasswordResetEmail(String email) {
        User user = userService.findByEmail(email);

        if (user != null) {
            // Generate a reset token
            String token = UUID.randomUUID().toString();

            // Set token and expiry time for validation
            user.setResetToken(token);
            user.setTokenExpiryTime(LocalDateTime.now().plusHours(1));  // Token valid for 1 hour

            userService.saveUser(user);  // Save token and expiry time in database

            // Construct the reset link with the token
            String resetLink = resetPasswordUrl + "?token=" + token;

            // Send the email with the reset link
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Password Reset Request");
            message.setText("To reset your password, click the following link: " + resetLink);
            mailSender.send(message);

            return true;
        }
        return false;  // Return false if the email does not exist in the database
    }

    public boolean resetPassword(String token, String newPassword) {
        // Validate token by checking if a user exists with the token
        User user = isValidToken(token);

        if (user != null) {
            // Hash the new password
            String encodedPassword = passwordEncoder.encode(newPassword);

            // Log the new password encoding
            System.out.println("Encoded password: " + encodedPassword);

            // Update the user password with the new encoded password
            user.setPassword(encodedPassword);

            // clear the reset token after resetting the password
            user.setResetToken(null);
            user.setTokenExpiryTime(null);

            // Save the updated user
            userService.saveUser(user);

            // Log the success
            System.out.println("Password reset successfully for user: " + user.getEmail());

            return true;  // Password successfully reset
        }

        // Log failure case
        System.out.println("Invalid token or user not found.");
        return false;  // Token is invalid or user not found
    }



    public User isValidToken(String token) {
        User user = userService.findByResetToken(token);

        if (user != null && user.getTokenExpiryTime() != null && user.getTokenExpiryTime().isAfter(LocalDateTime.now())) {
            return user;  // Token is valid
        }
        return null;  // Token invalid or expired
    }

}
