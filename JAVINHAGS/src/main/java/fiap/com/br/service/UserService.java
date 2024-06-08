package fiap.com.br.service;

import fiap.com.br.exception.EmailInUseException;
import fiap.com.br.exception.SenhaIncorretaException;
import fiap.com.br.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public void createUser(User user) throws EmailInUseException {
        if (users.containsKey(user.getEmail())) {
            throw new EmailInUseException("Email already in use");
        }
        users.put(user.getEmail(), user);
    }

    public User getUserByEmail(String email) {
        return users.get(email);
    }

    public void updateUser(User user) {
        users.put(user.getEmail(), user);
    }

    public void deleteUser(String email) {
        users.remove(email);
    }

    public void loginUser(String email, String password) throws SenhaIncorretaException {
        User user = users.get(email);
        if (user == null || !user.getPassword().equals(password)) {
            throw new SenhaIncorretaException("Incorrect email or password");
        }
    }
}
