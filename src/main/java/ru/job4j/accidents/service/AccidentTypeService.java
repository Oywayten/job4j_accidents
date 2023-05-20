package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeMem;

import java.util.Collection;

/**
 * Oywayten 19.05.2023.
 */
@Service
@AllArgsConstructor
public class AccidentTypeService {
    private final AccidentTypeMem accidentTypeMem;

    public Collection<AccidentType> getAll() {
        return accidentTypeMem.getAll();
    }
}
