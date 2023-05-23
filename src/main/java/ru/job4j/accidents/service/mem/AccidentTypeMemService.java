package ru.job4j.accidents.service.mem;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.mem.AccidentTypeMemRepository;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.Collection;

/**
 * Oywayten 19.05.2023.
 */
@Service
public class AccidentTypeMemService implements AccidentTypeService {
    private final AccidentTypeMemRepository accidentTypeMemRepository;

    public AccidentTypeMemService(AccidentTypeMemRepository accidentTypeMemRepository) {
        this.accidentTypeMemRepository = accidentTypeMemRepository;
    }

    @Override
    public Collection<AccidentType> getAll() {
        return accidentTypeMemRepository.getAll();
    }
}
