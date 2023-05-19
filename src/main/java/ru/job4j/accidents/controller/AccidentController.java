package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

import java.util.Optional;

import static ru.job4j.accidents.util.Util.goToError;
import static ru.job4j.accidents.util.Util.setUser;

/**
 * Oywayten 19.05.2023.
 */
@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidents;

    @GetMapping("/addAccident")
    public String viewCreateAccident(Model model) {
        setUser(model);
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        accidents.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String viewEditAccident(Model model, @RequestParam int id) {
        Optional<Accident> accidentOptional = accidents.getById(id);
        setUser(model);
        if (accidentOptional.isEmpty()) {
            return goToError(model, String.format("Open edit form error for accident with id = %d",
                    id));
        }
        model.addAttribute("accident", accidentOptional.orElse(null));
        return "editAccident";
    }
}