package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Oywayten 20.05.2023.
 */
public interface RuleRepository {
    Collection<Rule> getAll();

    Optional<Rule> getById(int id);

    default Set<Rule> getByIds(int[] rIds) {
        Set<Rule> ruleSet = new HashSet<>();
        Optional<Rule> ruleOptional;
        for (int rId : rIds) {
            ruleOptional = getById(rId);
            ruleOptional.ifPresent(ruleSet::add);
        }
        return ruleSet;
    }
}
