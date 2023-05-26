package ru.job4j.accidents.service.springdata;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.UserRepository;

/**
 * Oywayten 22.05.2023.
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserSpringDataService {
    private final UserRepository  userRepository;

    public boolean save(User user) {
        boolean result = false;
        try {
            userRepository.save(user);
            result = true;
        } catch (DataIntegrityViolationException e) {
            log.error("Error save user", e);
        }
        return result;
    }
}
