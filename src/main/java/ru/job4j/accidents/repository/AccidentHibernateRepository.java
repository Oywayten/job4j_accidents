package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

/**
 * Oywayten 22.05.2023.
 */
@Repository
@AllArgsConstructor
public class AccidentHibernateRepository {
    private final SessionFactory sf;

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(accident);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e;
            }
            return accident;
        }
    }

    public Optional<Accident> getById(int id) {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Accident AS a JOIN FETCH a.rules AS t WHERE a.id = :id", Accident.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        }
    }

    public List<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery("SELECT DISTINCT a FROM Accident AS a JOIN FETCH a.rules AS t", Accident.class).getResultList();
        }
    }


    public boolean update(Accident newAccident) {
        try (Session session = sf.openSession()) {
            Transaction transaction = null;
            Accident accident;
            try {
                transaction = session.beginTransaction();
                accident = session.get(Accident.class, newAccident.getId());
                accident.setName(newAccident.getName());
                accident.setText(newAccident.getText());
                accident.setAddress(newAccident.getAddress());
                accident.setType(newAccident.getType());
                accident.setRules(newAccident.getRules());
                accident = (Accident) session.merge(accident);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e;
            }
            return newAccident.equals(accident);
        }
    }
}
