package ru.job4j.accidents.repository.mem;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

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
public class AccidentMemRepository implements AccidentRepository {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public Collection<Accident> getAll() {
        return accidents.values();
    }

    @Override
    public Accident save(Accident newAccident) {
        int id = atomicInteger.incrementAndGet();
        newAccident.setId(id);
        accidents.put(id, newAccident);
        return newAccident;
    }

    @Override
    public Optional<Accident> getById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    @Override
    public boolean update(Accident newAccident) {
        Accident accident = accidents.computeIfPresent(newAccident.getId(), (integer, accident1) -> newAccident);
        return Objects.equals(accident, newAccident);
    }
}
