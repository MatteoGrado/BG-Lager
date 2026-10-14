package de.bg.lagersystem.controller;

import de.bg.lagersystem.model.Storage;
import de.bg.lagersystem.service.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
public class EditItemController {
    private final StorageService storageService;

    public EditItemController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/dashboard/editItem/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Storage product = storageService.findById(id);
        model.addAttribute("product", product);
        return "edit_item/edit_item";
    }

    @PostMapping("/dashboard/editItem/{id}")
    public String save(@PathVariable Long id,
                       @RequestParam String productName,
                       @RequestParam Integer shelfNumber,
                       @RequestParam Integer stock,
                       @RequestParam BigDecimal price,
                       @RequestParam String deliveryTime,
                       @RequestParam String seller) {
        storageService.update(id, productName, shelfNumber, stock, price,
                LocalDateTime.parse(deliveryTime), seller);
        return "redirect:/dashboard";
    }
}
