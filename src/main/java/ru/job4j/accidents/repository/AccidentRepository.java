package ru.job4j.accidents.repository;

import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;

import java.util.List;

/**
 * Oywayten 20.05.2023.
 */
public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Override
    @NonNull
    List<Accident> findAll();
}
