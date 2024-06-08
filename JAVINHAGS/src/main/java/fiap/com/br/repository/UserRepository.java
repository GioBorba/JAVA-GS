package fiap.com.br.repository;

import fiap.com.br.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public void save(User user) {
        users.put(user.getEmail(), user);
    }

    public User findByEmail(String email) {
        return users.get(email);
    }

    public void update(User user) {
        users.put(user.getEmail(), user);
    }

    public void deleteByEmail(String email) {
        users.remove(email);
    }
}
