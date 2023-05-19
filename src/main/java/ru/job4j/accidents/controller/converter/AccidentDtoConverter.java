package ru.job4j.accidents.controller.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.job4j.accidents.controller.dto.AccidentDto;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeMem;

import java.util.Optional;

/**
 * Oywayten 19.05.2023.
 */
@Component
@AllArgsConstructor
public class AccidentDtoConverter {
    private final AccidentTypeMem accidentTypeMem;
    public Accident convertToAccident(AccidentDto accidentDto) {
        Accident accident = new Accident();
        accident.setId(accidentDto.getId());
        accident.setName(accidentDto.getName());
        accident.setText(accidentDto.getText());
        accident.setAddress(accidentDto.getAddress());
        int typeId = accidentDto.getTypeId();
        Optional<AccidentType> accidentTypeOptional = accidentTypeMem.getById(typeId);
        accident.setType(accidentTypeOptional.orElseThrow());
        return accident;
    }
}
