package ru.job4j.accidents.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.AuthorityRepository;
import ru.job4j.accidents.service.springdata.UserSpringDataService;

/**
 * Oywayten 23.05.2023.
 */
@Controller
public class RegController {

    private final PasswordEncoder encoder;
    private final UserSpringDataService userSpringDataService;
    private final AuthorityRepository authorities;

    public RegController(
            PasswordEncoder encoder, UserSpringDataService userSpringDataService, AuthorityRepository authorities) {
        this.encoder = encoder;
        this.userSpringDataService = userSpringDataService;
        this.authorities = authorities;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        if (!userSpringDataService.save(user)) {
            String errorMessage = "Username already exist!";
            model.addAttribute("errorMessage", errorMessage);
            return "/reg";
        }
        return "redirect:/index";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}