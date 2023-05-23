package ru.job4j.accidents.repository.mem;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Oywayten 19.05.2023.
 */
@Repository
public class AccidentTypeMemRepository implements AccidentTypeRepository {
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public AccidentTypeMemRepository() {
        save("Two cars");
        save("Machine and man");
        save("Car and bike");
    }

    private void save(String name) {
        AccidentType newAccidentType = new AccidentType(atomicInteger.getAndIncrement(), name);
        types.put(newAccidentType.getId(), newAccidentType);
    }

    @Override
    public Collection<AccidentType> getAll() {
        return types.values();
    }

    @Override
    public Optional<AccidentType> getById(int id) {
        return Optional.ofNullable(types.get(id));
    }
}
