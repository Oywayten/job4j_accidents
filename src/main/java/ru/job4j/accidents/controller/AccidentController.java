package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.controller.dto.AccidentDto;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.Optional;

import static ru.job4j.accidents.util.Util.setUser;

/**
 * Oywayten 19.05.2023.
 */
@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidents;
    private final AccidentTypeService accidentTypeService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidentTypeService.getAll());
        setUser(model);
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute AccidentDto accidentDto, @RequestParam("type.id") int typeId) {
        accidentDto.setTypeId(typeId);
        accidents.create(accidentDto);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String viewEditAccident(Model model, @RequestParam int id) {
        Optional<Accident> accidentOptional = accidents.getById(id);
        setUser(model);
        model.addAttribute("types", accidentTypeService.getAll());
        model.addAttribute("accident", accidentOptional.orElseThrow());
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute AccidentDto accidentDto, @RequestParam("type.id") int typeId) {
        accidentDto.setTypeId(typeId);
        accidents.update(accidentDto);
        return "redirect:/index";
    }
}