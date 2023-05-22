package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

/**
 * Oywayten 20.05.2023.
 */
public interface AccidentTypeRepository {

    Collection<AccidentType> getAll();

    Optional<AccidentType> getById(int id);
}
