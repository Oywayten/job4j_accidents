package ru.job4j.accidents.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Oywayten 19.05.2023.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccidentDto {

    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private String text;
    private String address;
    private int typeId;
}