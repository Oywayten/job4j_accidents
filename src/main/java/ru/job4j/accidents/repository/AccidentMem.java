package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Oywayten 19.05.2023.
 */
@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    {
        dataInit();
    }

    private void dataInit() {
        String name;
        String text;
        String address;
        for (int i = 0; i < 10; i++) {
            name = String.format("%s%d", "name", i);
            text = String.format("%s%d", "text", i);
            address = String.format("%s%d", "address", i);
            add(new Accident(0, name, text, address));
        }
    }

    public Collection<Accident> getAll() {
        return accidents.values();
    }

    public Accident add(Accident accident) {
        int id = atomicInteger.getAndIncrement();
        accident.setId(id);
        accidents.put(id, accident);
        return accident;
    }
}
