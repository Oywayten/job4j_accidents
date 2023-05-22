package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.dto.AccidentDto;
import ru.job4j.accidents.mapper.AccidentMapper;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentJdbcRepository;
import ru.job4j.accidents.repository.RuleJdbcRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        List<Accident> accidents = accidentJdbcRepository.getAll();
        for (Accident accident : accidents) {
            setRulesToAccident(accident);
        }
        return accidents;
    }

    private void setRulesToAccident(Accident accident) {
        Set<Rule> rules = ruleJdbcRepository.getByAccidentId(accident.getId());
        accident.setRules(rules);
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
        Optional<Accident> accidentOptional = accidentJdbcRepository.getById(id);
        if (accidentOptional.isPresent()) {
            Accident accident = accidentOptional.get();
            setRulesToAccident(accident);
        }
        return accidentOptional;
    }

    @Override
    public boolean update(AccidentDto accidentDto) {
        Accident accident = accidentMapper.convertToAccident(accidentDto);
        ruleJdbcRepository.saveAccidentsRules(accidentDto.getRIds(), accident.getId());
        return accidentJdbcRepository.update(accident);
    }
}
