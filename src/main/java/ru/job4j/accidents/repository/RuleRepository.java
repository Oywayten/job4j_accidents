package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Optional;

/**
 * Oywayten 20.05.2023.
 */
public interface RuleRepository {
    Collection<Rule> getAll();

    Optional<Rule> getById(int id);
}
