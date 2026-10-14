package de.bg.lagersystem.controller;

import de.bg.lagersystem.model.Storage;
import de.bg.lagersystem.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DashboardController
{
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }
    
    @GetMapping("/dashboard")
    public String dashboard(@RequestParam(required = false) String search,
                            Model model)
    {
        List<Storage> products = dashboardService.findProducts(search);
        model.addAttribute("products", products);
        model.addAttribute("search", search == null ? "" : search);
        return "dashboard/dashboard";
    }
}
