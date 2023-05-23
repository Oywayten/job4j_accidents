package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;

/**
 * Oywayten 20.05.2023.
 */
public interface AccidentRepository extends CrudRepository<Accident, Integer> {
}
