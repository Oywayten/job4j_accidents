package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Oywayten 19.05.2023.
 */
@Repository
public class AccidentTypeMem {
    private final List<AccidentType> types = new ArrayList<>();
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public AccidentTypeMem() {
        add("Две машины");
        add("Машина и человек");
        add("Машина и велосипед");
    }

    public void add(String name) {
        AccidentType newAccidentType = new AccidentType(atomicInteger.getAndIncrement(), name);
        types.add(newAccidentType);
    }

    public List<AccidentType> getAll() {
        return types;
    }

    public Optional<AccidentType> getById(int id) {
        return Optional.of(types.get(id));
    }
}
