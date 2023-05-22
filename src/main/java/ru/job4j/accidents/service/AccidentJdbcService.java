package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.dto.AccidentDto;
import ru.job4j.accidents.mapper.AccidentMapper;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentJdbcRepository;
import ru.job4j.accidents.repository.RuleJdbcRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Oywayten 19.05.2023.
 */
@Service
public class AccidentJdbcService implements AccidentService {
    private final AccidentJdbcRepository accidentJdbcRepository;
    private final AccidentMapper accidentMapper;
    private final RuleJdbcRepository ruleJdbcRepository;

    public AccidentJdbcService(AccidentMapper accidentMapper,
                               AccidentJdbcRepository accidentJdbcRepository,
                               RuleJdbcRepository ruleJdbcRepository) {
        this.accidentJdbcRepository = accidentJdbcRepository;
        this.accidentMapper = accidentMapper;
        this.ruleJdbcRepository = ruleJdbcRepository;
    }

    @Override
    public Collection<Accident> getAll() {
        return accidentJdbcRepository.getAll();
    }

    @Override
    public Accident create(AccidentDto accidentDto) {
        Accident accident = accidentMapper.convertToAccident(accidentDto);
        accident = accidentJdbcRepository.save(accident);
        ruleJdbcRepository.saveAccidentsRules(accidentDto.getRIds(), accident.getId());
        return accident;
    }

    @Override
    public Optional<Accident> getById(int id) {
        return accidentJdbcRepository.getById(id);
    }

    @Override
    public boolean update(AccidentDto accidentDto) {
        Accident accident = accidentMapper.convertToAccident(accidentDto);
        ruleJdbcRepository.saveAccidentsRules(accidentDto.getRIds(), accident.getId());
        return accidentJdbcRepository.update(accident);
    }
}
