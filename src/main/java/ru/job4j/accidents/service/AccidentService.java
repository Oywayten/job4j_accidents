package ru.job4j.accidents.service;

import ru.job4j.accidents.dto.AccidentDto;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

/**
 * Oywayten 20.05.2023.
 */
public interface AccidentService {
    Collection<Accident> getAll();

    void create(AccidentDto accidentDto);

    Optional<Accident> getById(int id);

    boolean update(AccidentDto accidentDto);
}
