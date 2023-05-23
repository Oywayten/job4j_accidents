package ru.job4j.accidents.repository.mem;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Oywayten 19.05.2023.
 */
@Repository
public class AccidentMemRepository {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public Collection<Accident> getAll() {
        return accidents.values();
    }

    public Accident save(Accident newAccident) {
        int id = atomicInteger.incrementAndGet();
        newAccident.setId(id);
        accidents.put(id, newAccident);
        return newAccident;
    }

    public Optional<Accident> getById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    public boolean update(Accident newAccident) {
        Accident accident = accidents.computeIfPresent(newAccident.getId(), (integer, accident1) -> newAccident);
        return Objects.equals(accident, newAccident);
    }
}
