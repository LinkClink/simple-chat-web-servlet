package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import dao.UserDao;
import exception.DataProcessingException;
import model.User;
import util.ConnectionUtil;

public class UserDaoImpl implements UserDao {
    @Override
    public User save(User user) {
        String query = "INSERT INTO users (username) "
                + "VALUES (?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId((resultSet.getObject(1, Long.class)));
            }
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't save user "
                    + user.getUsername() + ". ", e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = parseUser(resultSet);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get user by id " + id, e);
        }
    }

    @Override
    public Optional<User> getByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = parseUser(resultSet);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get user by username " + username, e);
        }
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while ((resultSet.next())) {
                users.add(parseUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get all users ", e);
        }
    }

    private User parseUser(ResultSet resultSet) throws SQLException {
        Long userId = resultSet.getObject("id", Long.class);
        String username = resultSet.getString("username");
        return new User(userId, username);
    }
}
