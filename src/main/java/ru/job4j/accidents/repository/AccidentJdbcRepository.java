package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static ru.job4j.accidents.query.AccidentQuery.*;

/**
 * Oywayten 20.05.2023.
 */
@Repository
@AllArgsConstructor
public class AccidentJdbcRepository implements AccidentRepository {

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
                jdbc.query("SELECT *, at.name AS accident_type_name, r.id AS rule_id, r.name AS rule_name FROM accidents AS ac "
                        + "JOIN accident_types AS at ON ac.accident_type_id = at.id "
                        + "JOIN accidents_rules ar on ac.id = ar.accident_id "
                        + "JOIN rules r on ar.rule_id = r.id"
                        + " WHERE ac.id = ?", rs -> {
                    Accident accident = null;
                    while (rs.next()) {
                       accident = getAccident(rs);
                    }
                    return accident;
                }, id)
        );
    }

    private static Accident getAccident(ResultSet rs) throws SQLException {
        Accident accident = new Accident();
        Set<Rule> rules = new HashSet<>();
        accident.setRules(rules);
        accident.setId(rs.getInt(ID));
        accident.setName(rs.getString(NAME));
        accident.setText(rs.getString(TEXT));
        accident.setAddress(rs.getString(ADDRESS));
        AccidentType accidentType = new AccidentType();
        accidentType.setId(rs.getInt(ACCIDENT_TYPE_ID));
        accidentType.setName(rs.getString(ACCIDENT_TYPE_NAME));
        accident.setType(accidentType);
        Rule rule = new Rule();
        rule.setId(rs.getInt("rule_id"));
        rule.setName(rs.getString("rule_name"));
        rules.add(rule);
        return accident;
    }

    @Override
    public boolean update(Accident newAccident) {
        int updated = jdbc.update(UPDATE_ACCIDENTS_SET_NAME_TEXT_ADDRESS_ACCIDENT_TYPE_ID_WHERE_ID,
                newAccident.getName(), newAccident.getText(), newAccident.getAddress(), newAccident.getType().getId(),
                newAccident.getId());
        return updated > 0;
    }

    @Override
    public Collection<Accident> getAll() {
        return jdbc.query("SELECT *, at.name AS accident_type_name, r.id AS rule_id, r.name AS rule_name FROM accidents AS ac "
                + "JOIN accident_types AS at ON ac.accident_type_id = at.id "
                + "JOIN accidents_rules ar on ac.id = ar.accident_id "
                + "JOIN rules r on ar.rule_id = r.id", rs -> {
            Map<Integer, Accident> accidents = new HashMap<>();
            while (rs.next()) {
                Accident accident = getAccident(rs);
                accidents.merge(accident.getId(), accident, (accident1, accident2) -> {
                    Set<Rule> ruleSet1 = accident1.getRules();
                    Set<Rule> ruleSet2 = accident2.getRules();
                    ruleSet1.addAll(ruleSet2);
                    return accident1;
                });
            }
            return accidents.values();
        });
    }
}