package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Optional;

import static ru.job4j.accidents.query.AccidentQuery.ID;
import static ru.job4j.accidents.query.AccidentQuery.NAME;
import static ru.job4j.accidents.query.RuleQuery.*;

/**
 * Oywayten 19.05.2023.
 */
@Repository
@AllArgsConstructor
public class RuleJdbcRepository implements RuleRepository {
    private final RowMapper<Rule> ruleRowMapper = (rs, rowNum) -> {
        Rule rule = new Rule();
        rule.setId(rs.getInt(ID));
        rule.setName(rs.getString(NAME));
        return rule;
    };

    private final JdbcTemplate jdbc;

    @Override
    public List<Rule> getAll() {
        RowMapper<Rule> ruleRowMapper = (rs, rowNum) -> {
            Rule rule = new Rule();
            rule.setId(rs.getInt(ID));
            rule.setName(rs.getString(NAME));
            return rule;
        };
        return jdbc.query(SELECT_FROM_RULES, ruleRowMapper);
    }

    @Override
    public Optional<Rule> getById(int id) {
        return Optional.ofNullable(
                jdbc.queryForObject(SELECT_FROM_RULES_WHERE_ID, ruleRowMapper, id)
        );
    }

    public void saveAccidentsRules(int[] rIds, int id) {
        jdbc.update(DELETE_FROM_ACCIDENTS_RULES_WHERE_ACCIDENT_ID, id);
        for (int rId : rIds) {
            jdbc.update(INSERT_INTO_ACCIDENTS_RULES_ACCIDENT_ID_RULE_ID_VALUES, id, rId);
        }
    }
}
