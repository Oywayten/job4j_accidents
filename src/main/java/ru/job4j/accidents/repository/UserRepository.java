package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.User;

/**
 * Oywayten 23.05.2023.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}