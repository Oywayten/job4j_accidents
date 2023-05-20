package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Oywayten 19.05.2023.
 */
@Repository
public class RuleMem {
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public RuleMem() {
        add("Статья. 1");
        add("Статья. 2");
        add("Статья. 3");
    }

    public void add(String name) {
        Rule newRule = new Rule(atomicInteger.getAndIncrement(), name);
        rules.put(newRule.getId(), newRule);
    }

    public Collection<Rule> getAll() {
        return rules.values();
    }

    public Optional<Rule> getById(int id) {
        return Optional.ofNullable(rules.get(id));
    }
}
