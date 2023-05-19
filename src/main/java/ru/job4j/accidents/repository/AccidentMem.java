package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.controller.converter.AccidentDtoConverter;
import ru.job4j.accidents.controller.dto.AccidentDto;
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
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger atomicInteger = new AtomicInteger(0);
    private final AccidentDtoConverter accidentDtoConverter;

    public AccidentMem(AccidentDtoConverter accidentDtoConverter) {
        this.accidentDtoConverter = accidentDtoConverter;
        String name;
        String text;
        String address;
        for (int i = 1; i < 11; i++) {
            name = String.format("%s%d", "name", i);
            text = String.format("%s%d", "text", i);
            address = String.format("%s%d", "address", i);
            int typeId = i % 3;
            AccidentDto accidentDto = new AccidentDto(0, name, text, address, typeId);
            create(accidentDto);
        }
    }

    public Collection<Accident> getAll() {
        return accidents.values();
    }

    public void create(AccidentDto accidentDto) {
        Accident accident = accidentDtoConverter.convertToAccident(accidentDto);
        int id = atomicInteger.incrementAndGet();
        accident.setId(id);
        accidents.put(id, accident);
    }

    public Optional<Accident> getById(int id) {
        return Optional.of(accidents.get(id));
    }

    public void update(AccidentDto accidentDto) {
        Accident accident = accidentDtoConverter.convertToAccident(accidentDto);
        accidents.put(accident.getId(), accident);
    }
}
