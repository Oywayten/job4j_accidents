package ru.job4j.accidents.util;

import org.springframework.ui.Model;

/**
 * Oywayten 19.05.2023.
 */
public final class Util {

    private Util() {
    }

    public static void setUser(Model model) {
        model.addAttribute("user", "Petr Arsentev");
    }
}
