package dao;

import java.util.Optional;
import model.User;

public interface UserDao extends GenericDao<User> {
    Optional<User> getByUsername(String username);
}
