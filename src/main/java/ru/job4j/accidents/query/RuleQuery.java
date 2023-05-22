package ru.job4j.accidents.query;

import static ru.job4j.accidents.query.AccidentQuery.WHERE_ID;

/**
 * Oywayten 22.05.2023.
 */
public class RuleQuery {

    public static final String RULE_ID = "rule_id";
    public static final String SELECT_FROM_RULES = "SELECT * FROM rules";
    public static final String SELECT_FROM_RULES_WHERE_ID = String.format("%s %s", SELECT_FROM_RULES, WHERE_ID);
    public static final String SELECT_FROM_ACCIDENTS_RULES = "SELECT * FROM accidents_rules AS ar JOIN rules AS ru ON ar.rule_id = ru.id";
    public static final String WHERE_AR_ACCIDENT_ID = "WHERE ar.accident_id = ?";
    public static final String SELECT_FROM_ACCIDENTS_RULES_WHERE_ACCIDENT_ID =
            String.format("%s %s", SELECT_FROM_ACCIDENTS_RULES, WHERE_AR_ACCIDENT_ID);

    public static final String DELETE_FROM_ACCIDENTS_RULES = "DELETE FROM accidents_rules AS ar";
    public static final String DELETE_FROM_ACCIDENTS_RULES_WHERE_ACCIDENT_ID =
            String.format("%s %s", DELETE_FROM_ACCIDENTS_RULES, WHERE_AR_ACCIDENT_ID);
    public static final String INSERT_INTO_ACCIDENTS_RULES_ACCIDENT_ID_RULE_ID_VALUES =
            "INSERT INTO accidents_rules (accident_id, rule_id) values (?, ?)";
}
