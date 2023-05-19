package ru.job4j.accidents.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Oywayten 19.05.2023.
 */
@Repository
@Slf4j
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public AccidentMem() {
        String name;
        String text;
        String address;
        for (int i = 1; i < 11; i++) {
            name = String.format("%s%d", "name", i);
            text = String.format("%s%d", "text", i);
            address = String.format("%s%d", "address", i);
            create(new Accident(0, name, text, address));
        }
    }

    public Collection<Accident> getAll() {
        return accidents.values();
    }

    public void create(Accident accident) {
        int id = accident.getId();
        if (id == 0) {
            id = atomicInteger.incrementAndGet();
            accident.setId(id);
        }
        accidents.put(id, accident);
    }

    public Optional<Accident> getById(int id) {
        Optional<Accident> accidentOptional = Optional.empty();
        try {
            accidentOptional = Optional.of(accidents.get(id));
        } catch (Exception e) {
            log.error("Find accident by id error", e);
        }
        return accidentOptional;
    }
}
