package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Oywayten 19.05.2023.
 */
@Repository
public class RuleMemRepository implements RuleRepository {
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public RuleMemRepository() {
        save("Article. 1");
        save("Article. 2");
        save("Article. 3");
    }

    private void save(String name) {
        Rule newRule = new Rule(atomicInteger.getAndIncrement(), name);
        rules.put(newRule.getId(), newRule);
    }

    @Override
    public Collection<Rule> getAll() {
        return rules.values();
    }

    @Override
    public Optional<Rule> getById(int id) {
        return Optional.ofNullable(rules.get(id));
    }

    public Set<Rule> getByIds(int[] rIds) {
        Set<Rule> ruleSet = new HashSet<>();
        Optional<Rule> ruleOptional;
        for (int rId : rIds) {
            ruleOptional = getById(rId);
            ruleOptional.ifPresent(ruleSet::add);
        }
        return ruleSet;
    }
}