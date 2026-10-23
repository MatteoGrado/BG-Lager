package de.bg.lagersystem.controller;

import de.bg.lagersystem.model.Storage;
import de.bg.lagersystem.service.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class UpdateStorageController {
    private final StorageService storageService;

    public UpdateStorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/update-storage/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam(required = false) String action,
                         Model model) {
        Storage item = storageService.findById(id);
        model.addAttribute("storage", item);
        model.addAttribute("action", action == null ? "" : action);
        return "update_Storage/updateStorage";
    }

    @PostMapping("/update-storage/{id}")
    public String save(@PathVariable Long id,
                       @RequestParam Integer stock,
                       @RequestParam String deliveryTime,
                       @RequestParam(required = false, defaultValue = "") String firstName,
                       @RequestParam(required = false, defaultValue = "") String lastName,
                       @RequestParam(required = false, defaultValue = "") String useFor,
                       @RequestParam(required = false, defaultValue = "") String action) {
        if ("plus".equals(action) || "minus".equals(action)) {
            storageService.changeStock(id, "plus".equals(action) ? 1 : -1,
                    firstName, lastName);
        } else {
            storageService.updateStock(id, stock, LocalDateTime.parse(deliveryTime),
                    firstName, lastName, useFor);
        }

        return "redirect:/dashboard";
    }

}
