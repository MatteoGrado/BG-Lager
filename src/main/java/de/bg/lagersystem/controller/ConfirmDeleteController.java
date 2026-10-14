package de.bg.lagersystem.controller;

import de.bg.lagersystem.service.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConfirmDeleteController
{
    private final StorageService storageService;

    public ConfirmDeleteController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/dashboard/confirmDelete/{id}")
    public String form(@PathVariable Long id, Model model)
    {
        storageService.findById(id);
        model.addAttribute("id", id);
        return "confirm_delete/confirm_delete";
    }

    @PostMapping("/dashboard/confirmDelete/{id}")
    public String delete(@PathVariable Long id, @RequestParam String password)
    {
        storageService.findById(id);
        if ("Sumafelo".equals(password)) {
            storageService.delete(id);
        }
        return "redirect:/dashboard";
    }
}
