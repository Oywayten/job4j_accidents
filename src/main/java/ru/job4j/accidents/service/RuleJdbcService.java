package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleJdbcRepository;

import java.util.Collection;

/**
 * Oywayten 19.05.2023.
 */
@Service
public class RuleJdbcService implements RuleService {
    private final RuleJdbcRepository ruleJdbcRepository;

    public RuleJdbcService(RuleJdbcRepository ruleJdbcRepository) {
        this.ruleJdbcRepository = ruleJdbcRepository;
    }

    @Override
    public Collection<Rule> getAll() {
        return ruleJdbcRepository.getAll();
    }
}
