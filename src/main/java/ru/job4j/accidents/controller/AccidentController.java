package ru.job4j.accidents.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.dto.AccidentDto;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.RuleService;

import java.util.Optional;

import static ru.job4j.accidents.util.Util.goToError;
import static ru.job4j.accidents.util.Util.setUser;

/**
 * Oywayten 19.05.2023.
 */
@Controller
public class AccidentController {
    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    public AccidentController(@Qualifier("accidentHibernateService") AccidentService accidentService,
                              @Qualifier("accidentTypeJdbcService") AccidentTypeService accidentTypeService,
                              @Qualifier("ruleJdbcService") RuleService ruleService) {
        this.accidentService = accidentService;
        this.accidentTypeService = accidentTypeService;
        this.ruleService = ruleService;
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidentTypeService.getAll());
        model.addAttribute("rules", ruleService.getAll());
        setUser(model);
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute AccidentDto accidentDto) {
        accidentService.create(accidentDto);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String viewEditAccident(Model model, @RequestParam int id) {
        Optional<Accident> accidentOptional = accidentService.getById(id);
        setUser(model);
        if (accidentOptional.isEmpty()) {
            return goToError(model, String.format("Open edit form error for accident with id = %d", id));
        }
        model.addAttribute("types", accidentTypeService.getAll());
        model.addAttribute("rules", ruleService.getAll());
        model.addAttribute("accident", accidentOptional.get());
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String update(Model model, @ModelAttribute AccidentDto accidentDto) {
        if (!accidentService.update(accidentDto)) {
            setUser(model);
            return goToError(model, String.format("Open edit form error for accident with id = %d", accidentDto.getId()));
        }
        return "redirect:/index";
    }
}