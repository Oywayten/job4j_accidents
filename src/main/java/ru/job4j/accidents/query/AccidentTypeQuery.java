package ru.job4j.accidents.query;

import static ru.job4j.accidents.query.AccidentQuery.WHERE_ID;

/**
 * Oywayten 22.05.2023.
 */
public class AccidentTypeQuery {

    public static final String SELECT_FROM_ACCIDENT_TYPE = "SELECT * FROM accident_types";
    public static final String SELECT_FROM_ACCIDENT_TYPE_WHERE_ID = String.format("%s %s", SELECT_FROM_ACCIDENT_TYPE, WHERE_ID);
}
