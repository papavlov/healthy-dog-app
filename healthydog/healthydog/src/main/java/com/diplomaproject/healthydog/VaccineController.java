package com.diplomaproject.healthydog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/dogs/{dogId}/vaccines")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;
    @Autowired
    private DogService dogService;
    @Autowired
    private VaccineReminderService vaccineReminderService;  // Inject the VaccineReminderService

    // Show add vaccine form page
    @GetMapping("/add")
    public String showAddVaccinePage(@PathVariable Long dogId, Model model) {
        Dog dog = dogService.findById(dogId);
        if (dog == null) {
            return "redirect:/error";  // Handle invalid dog case
        }
        model.addAttribute("dog", dog);  // Pass the dog to the form
        model.addAttribute("vaccine", new Vaccine());
        model.addAttribute("vaccineNames", VaccineName.values());  // Add available vaccine options
        return "add_vaccine";  // Thymeleaf template
    }

    // Submit vaccine form and redirect to dog list page
    @PostMapping
    public String logVaccine(@PathVariable Long dogId,
                             @RequestParam VaccineName vaccineName,  // Enum instead of String
                             @RequestParam LocalDate vaccinationDate) {
        // Call the addVaccine method from VaccineService
        vaccineService.addVaccine(dogId, vaccineName, vaccinationDate);

        // Redirect to the dog list page after saving the vaccine
        return "redirect:/dogs/list";
    }

    // View vaccines page (for Thymeleaf)
    @GetMapping("/view")
    public String viewVaccines(@PathVariable Long dogId, Model model) {
        List<Vaccine> vaccines = vaccineService.getVaccinesForDog(dogId);
        Dog dog = dogService.findById(dogId);

        // Get vaccine reminder for the dog
        String vaccineReminder = vaccineReminderService.getVaccineReminder(dog);

        model.addAttribute("dog", dog);
        model.addAttribute("vaccines", vaccines);
        model.addAttribute("vaccineReminder", vaccineReminder);  // Add the reminder to the model

        return "dog_vaccines";  // Returns the Thymeleaf page
    }
    @GetMapping("/delete/{id}")
    public String deleteVaccine(@PathVariable Long id) {
        // Delete vaccine and redirect to vaccine history page
        vaccineService.deleteVaccine(id);
        return "redirect:/dogs/{dogId}/vaccines/view";
    }
}
