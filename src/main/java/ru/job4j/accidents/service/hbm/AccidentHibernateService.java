package ru.job4j.accidents.service.hbm;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.dto.AccidentDto;
import ru.job4j.accidents.mapper.AccidentMapper;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.hbm.AccidentHibernateRepository;
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
public class AccidentHibernateService implements AccidentService {
    private final AccidentHibernateRepository accidentHibernateRepository;
    private final AccidentMapper accidentMapper;
    private final RuleJdbcRepository ruleJdbcRepository;

    @Override
    public void create(AccidentDto accidentDto) {
        Accident accident = accidentMapper.convertToAccident(accidentDto);
        Set<Rule> rules = ruleJdbcRepository.getByIds(accidentDto.getRIds());
        accident.setRules(rules);
        accidentHibernateRepository.save(accident);
    }

    @Override
    public Optional<Accident> getById(int id) {
        return accidentHibernateRepository.getById(id);
    }

    @Override
    public List<Accident> getAll() {
        return accidentHibernateRepository.getAll();
    }

    @Override
    public boolean update(AccidentDto accidentDto) {
        Accident accident = accidentMapper.convertToAccident(accidentDto);
        Set<Rule> rules = ruleJdbcRepository.getByIds(accidentDto.getRIds());
        accident.setRules(rules);
        return accidentHibernateRepository.update(accident);
    }
}

