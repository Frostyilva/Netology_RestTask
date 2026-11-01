package ru.netology.rest;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {

    private final Map<String, String> users = new ConcurrentHashMap<>();
    private final Map<String, List<Authorities>> authorities = new ConcurrentHashMap<>();

    public UserRepository() {
        users.put("admin", "admin");
        authorities.put("admin", List.of(Authorities.READ, Authorities.WRITE, Authorities.DELETE));

        users.put("user", "password");
        authorities.put("user", List.of(Authorities.READ));
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        String storedPassword = users.get(user);

        if (storedPassword == null || !storedPassword.equals(password)) {
            return List.of();
        }

        return authorities.getOrDefault(user, List.of());
    }
}
