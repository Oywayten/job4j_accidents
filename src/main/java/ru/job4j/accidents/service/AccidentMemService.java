package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.dto.AccidentDto;
import ru.job4j.accidents.mapper.AccidentMapper;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentMemRepository;
import ru.job4j.accidents.repository.RuleMemRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * Oywayten 19.05.2023.
 */
@Service
public class AccidentMemService implements AccidentService {
    private final AccidentMemRepository accidentMemRepository;
    private final AccidentMapper accidentMapper;
    private final RuleMemRepository ruleMemRepository;

    public AccidentMemService(AccidentMemRepository accidentMemRepository, AccidentMapper accidentMapper, RuleMemRepository ruleMemRepository) {
        this.accidentMapper = accidentMapper;
        this.accidentMemRepository = accidentMemRepository;
        this.ruleMemRepository = ruleMemRepository;

        String name;
        String text;
        String address;
        int[] ids = new int[1];
        for (int i = 1; i < 3; i++) {
            name = String.format("%s%d", "name", i);
            text = String.format("%s%d", "text", i);
            address = String.format("%s%d", "address", i);
            ids[0] = i;
            AccidentDto accidentDto = new AccidentDto(0, name, text, address, i, ids);
            create(accidentDto);
        }
    }

    @Override
    public Collection<Accident> getAll() {
        return accidentMemRepository.getAll();
    }

    @Override
    public Accident create(AccidentDto accidentDto) {
        Accident accident = accidentMapper.convertToAccident(accidentDto);
        Set<Rule> rules = ruleMemRepository.getByIds(accidentDto.getRIds());
        accident.setRules(rules);
        return accidentMemRepository.save(accident);
    }

    @Override
    public Optional<Accident> getById(int id) {
        return accidentMemRepository.getById(id);
    }

    @Override
    public boolean update(AccidentDto accidentDto) {
        Accident accident = accidentMapper.convertToAccident(accidentDto);
        Set<Rule> rules = ruleMemRepository.getByIds(accidentDto.getRIds());
        accident.setRules(rules);
        return accidentMemRepository.update(accident);
    }
}
