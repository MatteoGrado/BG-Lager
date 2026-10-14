package de.bg.lagersystem.controller;

import de.bg.lagersystem.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StartController
{
    private final LoginService loginService;

    public StartController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String login(Model model)
    {
        model.addAttribute("username", "");
        model.addAttribute("password", "");
        return "start/index";
    }

    @PostMapping("/")
    public String authenticate(@RequestParam String username, @RequestParam String password, Model model)
    {
        try {
            loginService.authenticate(username, password);
            model.addAttribute("success", "Successfully logged in!");
            return "redirect:/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid Username or Password!");
            model.addAttribute("username", username);
            return "start/index";
        }
    }
}
