package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

/**
 * Oywayten 20.05.2023.
 */
public interface AccidentRepository {
    Collection<Accident> getAll();

    Accident save(Accident newAccident);

    Optional<Accident> getById(int id);

    boolean update(Accident newAccident);
}
