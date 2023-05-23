package ru.job4j.accidents.query;

/**
 * Oywayten 21.05.2023.
 */
public class AccidentQuery {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String TEXT = "text";
    public static final String ADDRESS = "address";
    public static final String ACCIDENT_TYPE_ID = "accident_type_id";
    public static final String ACCIDENT_TYPE_NAME = "accident_type_name";
    public static final String WHERE_ID = "WHERE id = ?";
    public static final String UPDATE_ACCIDENTS_SET_NAME_TEXT_ADDRESS_ACCIDENT_TYPE_ID =
            "UPDATE accidents SET name = ?, text = ?, address = ?, accident_type_id = ?";
    public static final String UPDATE_ACCIDENTS_SET_NAME_TEXT_ADDRESS_ACCIDENT_TYPE_ID_WHERE_ID =
            String.format("%s %s", UPDATE_ACCIDENTS_SET_NAME_TEXT_ADDRESS_ACCIDENT_TYPE_ID, WHERE_ID);
    public static final String INSERT_INTO_ACCIDENTS =
            "INSERT INTO accidents (name, text, address, accident_type_id) VALUES (?, ?, ?, ?)";
    public static final String SELECT_DISTINCT_A_FROM_ACCIDENT_AS_A_JOIN_FETCH_A_RULES_AS_T =
            "SELECT DISTINCT a FROM Accident AS a JOIN FETCH a.rules AS t";
    public static final String WHERE_A_ID_ID = "WHERE a.id = :id";
    public static final String SELECT_DISTINCT_A_FROM_ACCIDENT_AS_A_JOIN_FETCH_A_RULES_AS_T_WHERE_A_ID_ID =
            String.format("%s %s", SELECT_DISTINCT_A_FROM_ACCIDENT_AS_A_JOIN_FETCH_A_RULES_AS_T,
                    WHERE_A_ID_ID);
}
