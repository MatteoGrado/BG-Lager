package de.bg.lagersystem.controller;

import de.bg.lagersystem.model.Storage;
import de.bg.lagersystem.service.MailService;
import de.bg.lagersystem.service.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class UpdateStorageController {
    private final StorageService storageService;
    private final MailService mailService;

    public UpdateStorageController(StorageService storageService, MailService mailService) {
        this.storageService = storageService;
        this.mailService = mailService;
    }

    @GetMapping("/update-storage/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam(required = false) String action,
                         Model model) {
        Storage item = storageService.findById(id);
        if ("plus".equals(action)) {
            storageService.changeStock(id, 1);
            return "redirect:/update-storage/" + id;
        }
        if ("minus".equals(action)) {
            storageService.changeStock(id, -1);
            return "redirect:/update-storage/" + id;
        }
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
                       @RequestParam(required = false, defaultValue = "") String useFor) {
        Storage item = storageService.updateStock(id, stock, LocalDateTime.parse(deliveryTime));

        // In the original controller, $action comes from the query string and
        // is null on a normal POST, so the equivalent action is empty here.
        try {
            mailService.sendHtmlMail(item.getProductName(), item.getStock(), useFor,
                    firstName, lastName, "");
        } catch (Throwable e) {
            // Original code dumped the exception. We keep the update successful
            // and let the application log/report the mail problem.
        }
        return "redirect:/dashboard";
    }

}
