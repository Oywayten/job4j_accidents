package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ru.job4j.accidents.query.AccidentQuery.*;

/**
 * Oywayten 20.05.2023.
 */
@Repository
@AllArgsConstructor
public class AccidentJdbcRepository implements AccidentRepository {

    private final RowMapper<Accident> accidentRowMapper = (rs, rowNum) -> {
        Accident accident = new Accident();
        accident.setId(rs.getInt(ID));
        accident.setName(rs.getString(NAME));
        accident.setText(rs.getString(TEXT));
        accident.setAddress(rs.getString(ADDRESS));
        AccidentType accidentType = new AccidentType();
        accidentType.setId(rs.getInt(ACCIDENT_TYPE_ID));
        accidentType.setName(rs.getString(ACCIDENT_TYPE_NAME));
        accident.setType(accidentType);
        return accident;
    };

    private final JdbcTemplate jdbc;

    @Override
    public Accident save(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_INTO_ACCIDENTS, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId((Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id"));
        return accident;
    }

    @Override
    public Optional<Accident> getById(int id) {
        return Optional.ofNullable(
                jdbc.queryForObject(SELECT_FROM_ACCIDENTS_AS_AC_JOIN_ACCIDENT_TYPES_WHERE_AC_ID, accidentRowMapper, id)
        );
    }

    @Override
    public boolean update(Accident newAccident) {
        int updated = jdbc.update(UPDATE_ACCIDENTS_SET_NAME_TEXT_ADDRESS_ACCIDENT_TYPE_ID_WHERE_ID,
                newAccident.getName(), newAccident.getText(), newAccident.getAddress(), newAccident.getType().getId(),
                newAccident.getId());
        return updated > 0;
    }

    @Override
    public List<Accident> getAll() {
        return jdbc.query(SELECT_FROM_ACCIDENTS_AS_AC_JOIN_ACCIDENT_TYPES, accidentRowMapper);
    }
}