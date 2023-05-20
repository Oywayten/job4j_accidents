package ru.job4j.accidents.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.job4j.accidents.dto.AccidentDto;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentTypeMem;
import ru.job4j.accidents.repository.RuleMem;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Oywayten 19.05.2023.
 */
@Component
@AllArgsConstructor
public class AccidentMapper {
    private final AccidentTypeMem accidentTypeMem;
    private final RuleMem ruleMem;

    public Accident convertToAccident(AccidentDto accidentDto) {
        Accident accident = new Accident();
        accident.setId(accidentDto.getId());
        accident.setName(accidentDto.getName());
        accident.setText(accidentDto.getText());
        accident.setAddress(accidentDto.getAddress());
        int typeId = accidentDto.getTypeId();
        Optional<AccidentType> accidentTypeOptional = accidentTypeMem.getById(typeId);
        accidentTypeOptional.ifPresent(accident::setType);
        setRulesToAccident(accidentDto, accident);
        return accident;
    }

    private void setRulesToAccident(AccidentDto accidentDto, Accident accident) {
        Set<Rule> ruleSet = new HashSet<>();
        Optional<Rule> ruleOptional;
        Rule rule;
        for (int rId : accidentDto.getRIds()) {
            ruleOptional = ruleMem.getById(rId);
            if (ruleOptional.isPresent()) {
                rule = ruleOptional.get();
                ruleSet.add(rule);
            }
        }
        accident.setRules(ruleSet);
    }
}
