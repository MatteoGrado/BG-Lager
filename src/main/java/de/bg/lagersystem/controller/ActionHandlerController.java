package de.bg.lagersystem.controller;

import de.bg.lagersystem.service.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ActionHandlerController
{
    private final StorageService storageService;

    public ActionHandlerController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/dashboard/actionHandler/{id}")
    public String action(@PathVariable Long id, @RequestParam String action)
    {
        storageService.findById(id);
        return switch (action) {
            case "plus", "minus" -> "redirect:/update-storage/" + id + "?action=" + action;
            case "edit" -> "redirect:/dashboard/editItem/" + id;
            case "delete" -> "redirect:/dashboard/confirmDelete/" + id;
            default -> "redirect:/dashboard";
        };
    }
}
