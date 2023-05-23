package ru.job4j.accidents.service.jdbc;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.jdbc.AccidentTypeJdbcRepository;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.Collection;

/**
 * Oywayten 19.05.2023.
 */
@Service
public class AccidentTypeJdbcService implements AccidentTypeService {
    private final AccidentTypeJdbcRepository accidentTypeJdbcRepository;

    public AccidentTypeJdbcService(AccidentTypeJdbcRepository accidentTypeJdbcRepository) {
        this.accidentTypeJdbcRepository = accidentTypeJdbcRepository;
    }

    @Override
    public Collection<AccidentType> getAll() {
        return accidentTypeJdbcRepository.getAll();
    }
}
