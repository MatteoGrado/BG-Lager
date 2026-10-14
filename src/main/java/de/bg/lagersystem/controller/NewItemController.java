package de.bg.lagersystem.controller;

import de.bg.lagersystem.model.Storage;
import de.bg.lagersystem.service.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
public class NewItemController {
    private final StorageService storageService;

    public NewItemController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/dashboard/newItem")
    public String form(Model model) {
        model.addAttribute("deliveryTime", LocalDateTime.now().withNano(0));
        return "new_item/newItem";
    }

    @PostMapping("/dashboard/newItem")
    public String create(@RequestParam String productName,
                         @RequestParam Integer shelfNumber,
                         @RequestParam Integer stock,
                         @RequestParam BigDecimal price,
                         @RequestParam String deliveryTime,
                         @RequestParam String seller,
                         @RequestParam(required = false) String barcode) {
        storageService.create(productName, shelfNumber, stock, price,
                LocalDateTime.parse(deliveryTime), seller);
        return "redirect:/dashboard";
    }
}
