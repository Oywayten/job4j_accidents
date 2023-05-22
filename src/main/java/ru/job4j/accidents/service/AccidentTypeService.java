package ru.job4j.accidents.service;

import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;

/**
 * Oywayten 20.05.2023.
 */
public interface AccidentTypeService {
    Collection<AccidentType> getAll();
}
