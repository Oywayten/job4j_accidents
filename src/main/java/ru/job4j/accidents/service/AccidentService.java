package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.dto.AccidentDto;
import ru.job4j.accidents.mapper.AccidentMapper;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.Collection;
import java.util.Optional;

/**
 * Oywayten 19.05.2023.
 */
@Service
public class AccidentService {
    private final AccidentMem accidentMem;
    private final AccidentMapper accidentMapper;

    public AccidentService(AccidentMem accidentMem, AccidentMapper accidentMapper) {
        this.accidentMapper = accidentMapper;
        this.accidentMem = accidentMem;
        String name;
        String text;
        String address;
        for (int i = 1; i < 11; i++) {
            name = String.format("%s%d", "name", i);
            text = String.format("%s%d", "text", i);
            address = String.format("%s%d", "address", i);
            int typeId = i % 3;
            AccidentDto accidentDto = new AccidentDto(0, name, text, address, typeId);
            create(accidentDto);
        }
    }

    public Collection<Accident> getAll() {
        return accidentMem.getAll();
    }

    public void create(AccidentDto accidentDto) {
        Accident accident = accidentMapper.convertToAccident(accidentDto);
        accidentMem.create(accident);
    }

    public Optional<Accident> getById(int id) {
        return accidentMem.getById(id);
    }

    public boolean update(AccidentDto accidentDto) {
        Accident accident = accidentMapper.convertToAccident(accidentDto);
        return accidentMem.update(accident);
    }
}
