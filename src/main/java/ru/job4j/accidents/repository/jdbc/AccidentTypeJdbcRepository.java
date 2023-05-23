package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.Collection;
import java.util.Optional;

import static ru.job4j.accidents.query.AccidentQuery.ID;
import static ru.job4j.accidents.query.AccidentQuery.NAME;
import static ru.job4j.accidents.query.AccidentTypeQuery.SELECT_FROM_ACCIDENT_TYPE;
import static ru.job4j.accidents.query.AccidentTypeQuery.SELECT_FROM_ACCIDENT_TYPE_WHERE_ID;

/**
 * Oywayten 19.05.2023.
 */
@Repository
@AllArgsConstructor
public class AccidentTypeJdbcRepository implements AccidentTypeRepository {

    private final RowMapper<AccidentType> accidentTypeRowMapper = (rs, rowNum) -> {
        AccidentType accidentType = new AccidentType();
        accidentType.setId(rs.getInt(ID));
        accidentType.setName(rs.getString(NAME));
        return accidentType;
    };

    private final JdbcTemplate jdbc;

    @Override
    public Collection<AccidentType> getAll() {
        return jdbc.query(SELECT_FROM_ACCIDENT_TYPE, accidentTypeRowMapper);
    }

    @Override
    public Optional<AccidentType> getById(int id) {
        return Optional.ofNullable(
                jdbc.queryForObject(SELECT_FROM_ACCIDENT_TYPE_WHERE_ID, accidentTypeRowMapper, id)
        );
    }
}
