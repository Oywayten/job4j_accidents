package ru.job4j.accidents.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.service.AccidentService;

import static ru.job4j.accidents.util.Util.setUser;

/**
 * Oywayten 19.05.2023.
 */
@Controller
public class IndexController {
    private final AccidentService accidentService;

    public IndexController(@Qualifier("accidentMemService") AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping({"/", "/index", "/accidents"})
    public String index(Model model) {
        setUser(model);
        model.addAttribute("accidents", accidentService.getAll());
        return "index";
    }
}