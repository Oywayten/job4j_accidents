package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleMem;

import java.util.Collection;

/**
 * Oywayten 19.05.2023.
 */
@Service
@AllArgsConstructor
public class RuleService {
    private final RuleMem ruleMem;

    public Collection<Rule> getAll() {
        return ruleMem.getAll();
    }
}
