package ru.job4j.accidents;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Oywayten 23.05.2023.
 */
public class Main {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("secret");
        System.out.println(pwd);
    }
}