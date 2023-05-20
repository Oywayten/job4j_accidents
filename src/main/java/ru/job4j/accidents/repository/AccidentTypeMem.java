package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Oywayten 19.05.2023.
 */
@Repository
public class AccidentTypeMem {
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public AccidentTypeMem() {
        add("Две машины");
        add("Машина и человек");
        add("Машина и велосипед");
    }

    public void add(String name) {
        AccidentType newAccidentType = new AccidentType(atomicInteger.getAndIncrement(), name);
        types.put(newAccidentType.getId(), newAccidentType);
    }

    public Collection<AccidentType> getAll() {
        return types.values();
    }

    public Optional<AccidentType> getById(int id) {
        return Optional.ofNullable(types.get(id));
    }
}
