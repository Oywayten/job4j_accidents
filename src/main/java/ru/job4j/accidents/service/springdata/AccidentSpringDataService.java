package ru.job4j.accidents.service.springdata;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.dto.AccidentDto;
import ru.job4j.accidents.mapper.AccidentMapper;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentRepository;
import ru.job4j.accidents.repository.jdbc.RuleJdbcRepository;
import ru.job4j.accidents.service.AccidentService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Oywayten 22.05.2023.
 */
@Service
@AllArgsConstructor
public class AccidentSpringDataService implements AccidentService {
    private final AccidentRepository accidentRepository;
    private final AccidentMapper accidentMapper;
    private final RuleJdbcRepository ruleJdbcRepository;

    @Override
    public void create(AccidentDto accidentDto) {
        Accident accident = accidentMapper.convertToAccident(accidentDto);
        Set<Rule> rules = ruleJdbcRepository.getByIds(accidentDto.getRIds());
        accident.setRules(rules);
        accidentRepository.save(accident);
    }

    @Override
    public Optional<Accident> getById(int id) {
        return accidentRepository.findById(id);
    }

    public List<Accident> getAll() {
        return (List<Accident>) accidentRepository.findAll();
    }

    @Override
    public boolean update(AccidentDto accidentDto) {
        Accident accident = accidentMapper.convertToAccident(accidentDto);
        Set<Rule> rules = ruleJdbcRepository.getByIds(accidentDto.getRIds());
        accident.setRules(rules);
        Accident savedAccident = accidentRepository.save(accident);
        return accident.equals(savedAccident);
    }
}

