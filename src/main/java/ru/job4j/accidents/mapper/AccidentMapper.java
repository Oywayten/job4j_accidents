package ru.job4j.accidents.mapper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.job4j.accidents.dto.AccidentDto;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.Optional;

/**
 * Oywayten 19.05.2023.
 */
@Component
public class AccidentMapper {
    private final AccidentTypeRepository accidentTypeRepository;

    public AccidentMapper(@Qualifier("accidentTypeJdbcRepository") AccidentTypeRepository accidentTypeRepository) {
        this.accidentTypeRepository = accidentTypeRepository;
    }

    public Accident convertToAccident(AccidentDto accidentDto) {
        Accident accident = new Accident();
        accident.setId(accidentDto.getId());
        accident.setName(accidentDto.getName());
        accident.setText(accidentDto.getText());
        accident.setAddress(accidentDto.getAddress());
        int typeId = accidentDto.getTypeId();
        Optional<AccidentType> accidentTypeOptional = accidentTypeRepository.getById(typeId);
        accidentTypeOptional.ifPresent(accident::setType);
        return accident;
    }
}
