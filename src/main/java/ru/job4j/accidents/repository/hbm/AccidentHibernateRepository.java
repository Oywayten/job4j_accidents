package ru.job4j.accidents.repository.hbm;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ru.job4j.accidents.query.AccidentQuery.SELECT_DISTINCT_A_FROM_ACCIDENT_AS_A_JOIN_FETCH_A_RULES_AS_T;
import static ru.job4j.accidents.query.AccidentQuery.SELECT_DISTINCT_A_FROM_ACCIDENT_AS_A_JOIN_FETCH_A_RULES_AS_T_WHERE_A_ID_ID;

/**
 * Oywayten 22.05.2023.
 */
@Repository
@AllArgsConstructor
public class AccidentHibernateRepository implements AccidentRepository {

    private final CrudRepository crudRepository;

    @Override
    public Accident save(Accident accident) {
        return crudRepository.tx(session -> {
            session.persist(accident);
            return accident;
        });
    }

    @Override
    public Optional<Accident> getById(int id) {
        return crudRepository.optional(SELECT_DISTINCT_A_FROM_ACCIDENT_AS_A_JOIN_FETCH_A_RULES_AS_T_WHERE_A_ID_ID,
                Accident.class, Map.of("id", id));
    }

    @Override
    public List<Accident> getAll() {
        return crudRepository.query(SELECT_DISTINCT_A_FROM_ACCIDENT_AS_A_JOIN_FETCH_A_RULES_AS_T, Accident.class);
    }

    @Override
    public boolean update(Accident newAccident) {
        boolean result = false;
        Optional<Accident> dbAccidentOptional = getById(newAccident.getId());
        if (dbAccidentOptional.isPresent()) {
            crudRepository.run(session -> session.update(newAccident));
            result = true;
        }
        return result;
    }
}
