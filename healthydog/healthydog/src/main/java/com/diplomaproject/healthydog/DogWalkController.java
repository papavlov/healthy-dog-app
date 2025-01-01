package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/dogs/{dogId}/walks")
public class DogWalkController {

    @Autowired
    private DogWalkService dogWalkService;

    @Autowired
    private DogService dogService;

    // Show log walk form page
    @GetMapping("/add")
    public String showAddWalkPage(@PathVariable Long dogId, Model model) {
        Dog dog = dogService.findById(dogId);
        if (dog == null) {
            return "redirect:/dogs/error";  // Handle invalid dog case
        }
        model.addAttribute("dog", dog);  // Pass the dog to the form
        model.addAttribute("dogWalk", new DogWalk());  // Add a new DogWalk object to the form
        return "add_dog_walk";  // Thymeleaf template to log walk
    }

    @PostMapping("/add")
    public String logWalk(@PathVariable Long dogId,
                          @RequestParam Double distance,
                          @RequestParam LocalDate walkDate,
                          @RequestParam Integer duration,
                          RedirectAttributes redirectAttributes) {
        Dog dog = dogService.findById(dogId);
        if (dog == null) {
            redirectAttributes.addFlashAttribute("message", "Dog not found!");
            return "redirect:/dogs/error";  // Redirect if dog not found
        }
        // Log the walk
        dogWalkService.logWalk(dogId, distance, walkDate, duration);
        redirectAttributes.addFlashAttribute("message", "Walk logged successfully");
        return "redirect:/dogs/{dogId}/walks/history";  // Redirect to walk history
    }

    @GetMapping("/history")
    public String viewWalkHistory(@PathVariable Long dogId, Model model) {
        Dog dog = dogService.findById(dogId);
        if (dog == null) {
            return "redirect:/dogs/error";  // Handle invalid dog case
        }
        // Fetch the list of walks for the dog
        List<DogWalk> walks = dogWalkService.findWalksByDogId(dogId);
        // Calculate the total walked distance
        double totalWalked = walks.stream().mapToDouble(DogWalk::getDistance).sum();
        model.addAttribute("dog", dog);
        model.addAttribute("walks", walks);
        model.addAttribute("totalWalked", totalWalked);  // Pass total walked distance to view
        return "walk_history";  // Thymeleaf page for viewing walks
    }

    @GetMapping("/history/filter")
    public String filterWalksByDate(@PathVariable Long dogId,
                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate walkDate,
                                    Model model, RedirectAttributes redirectAttributes) {
        Dog dog = dogService.findById(dogId);
        if (dog == null) {
            return "redirect:/dogs/error";
        }

        if (walkDate == null) {
            redirectAttributes.addFlashAttribute("error", "Please select a date to filter walks.");
            return "redirect:/dogs/" + dogId + "/walks/history";  // Redirect back to the history page
        }

        // Fetch filtered walks by date
        List<DogWalk> walks = dogWalkService.findWalksByDogIdAndDate(dogId, walkDate);
        // Calculate the daily goal for the dog
        Double dailyGoal = dogService.calculateDailyGoal(dog);
        // Calculate the total walked distance for the selected date
        double totalWalked = walks.stream().mapToDouble(DogWalk::getDistance).sum();

        // Set display strings for goal and walked distances
        String goalDisplay = dailyGoal != null ? dailyGoal.toString() : "N/A";
        String walkedDisplay = totalWalked > 0 ? String.valueOf(totalWalked) : "0";

        model.addAttribute("dog", dog);
        model.addAttribute("walks", walks);
        model.addAttribute("selectedDate", walkDate);
        model.addAttribute("goalDisplay", goalDisplay);  // Pass goalDisplay to view
        model.addAttribute("walkedDisplay", walkedDisplay);  // Pass walkedDisplay to view
        model.addAttribute("totalWalked", totalWalked);  // Pass total walked distance to view
        return "walk_history";
    }

    @GetMapping("/delete/{id}")
    public String deleteWalk(@PathVariable Long id) {
        // Delete the walk and redirect to the history page
        dogWalkService.deleteWalk(id);
        return "redirect:/dogs/{dogId}/walks/history";  // Redirect to walk history after deletion
    }
}
