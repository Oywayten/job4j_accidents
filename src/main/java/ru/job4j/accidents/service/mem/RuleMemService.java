package ru.job4j.accidents.service.mem;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.mem.RuleMemRepository;
import ru.job4j.accidents.service.RuleService;

import java.util.Collection;

/**
 * Oywayten 19.05.2023.
 */
@Service
public class RuleMemService implements RuleService {
    private final RuleMemRepository ruleMemRepository;

    public RuleMemService(RuleMemRepository ruleMemRepository) {
        this.ruleMemRepository = ruleMemRepository;
    }

    @Override
    public Collection<Rule> getAll() {
        return ruleMemRepository.getAll();
    }
}
