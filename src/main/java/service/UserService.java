package service;

import java.util.Optional;
import model.User;

public interface UserService {
    User get(Long id);

    User save(User user);

    Optional<User> getByUsername(String username);
}
