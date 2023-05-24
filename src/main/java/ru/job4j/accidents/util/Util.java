package ru.job4j.accidents.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

/**
 * Oywayten 19.05.2023.
 */
public final class Util {

    private Util() {
    }

    public static void setUser(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public static String goToError(Model model, String message) {
        model.addAttribute("message", message);
        return "/error";
    }
}
