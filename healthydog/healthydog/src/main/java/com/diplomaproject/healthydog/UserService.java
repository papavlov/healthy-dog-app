package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Retrieve all users
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Retrieve a user by ID
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    // Save a new user (register)
    public User saveUser(User user) {

        return userRepository.save(user);
    }


    // Update an existing user
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(userDetails.getEmail());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        // Update password only if its provided
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        return userRepository.save(user);
    }

    // Delete a user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    //find a user by email for authentication purposes
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    //public User findByUsername(String username) {
      //  return userRepository.findByUsername(username);
    //}

    // Find user by reset token
    public User findByResetToken(String token) {
        return userRepository.findByResetToken(token);
    }


    // Set reset token and expiration for password reset
    public void setResetToken(User user, String token) {
        user.setResetToken(token);
        user.setTokenExpiryTime(LocalDateTime.now().plusHours(1)); // Token valid for 1 hour
        userRepository.save(user);
    }

    // Clear the reset token after successful reset
    public void clearResetToken(User user) {
        user.setResetToken(null);
        user.setTokenExpiryTime(null);
        userRepository.save(user);
    }

}
