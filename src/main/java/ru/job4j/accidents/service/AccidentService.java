package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.controller.dto.AccidentDto;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.Collection;
import java.util.Optional;

/**
 * Oywayten 19.05.2023.
 */
@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentMem accidentMem;

    public Collection<Accident> getAll() {
        return accidentMem.getAll();
    }

    public void create(AccidentDto accidentDto) {
        accidentMem.create(accidentDto);
    }

    public Optional<Accident> getById(int id) {
        return accidentMem.getById(id);
    }

    public void update(AccidentDto accidentDto) {
        accidentMem.update(accidentDto);
    }
}
