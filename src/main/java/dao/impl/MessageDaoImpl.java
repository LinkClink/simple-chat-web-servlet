package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import dao.MessageDao;
import model.Message;
import util.ConnectionUtil;

public class MessageDaoImpl implements MessageDao {

    @Override
    public Message send(Message message) {
        String query = "INSERT INTO messages (username, content, send_time) "
                + "VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, message.getUsername());
            statement.setString(2, message.getContent());
            statement.setString(3, message.getDataTime());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                message.setId((resultSet.getObject(1, Long.class)));
            }
            return message;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't send message "
                    + message.getContent() + ". ", e);
        }
    }

    @Override
    public Optional<Message> get(Long id) {
        String query = "SELECT * FROM messages WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Message message = null;
            if (resultSet.next()) {
                message = parseMessage(resultSet);
            }
            return Optional.ofNullable(message);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't get message by id " + id, e);
        }
    }

    @Override
    public List<Message> getAll() {
        String query = "SELECT * FROM messages";
        List<Message> drivers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                drivers.add(parseMessage(resultSet));
            }
            return drivers;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't get a list of messages from database.",
                    e);
        }
    }

    @Override
    public List<Message> getLimitAsc(int limit) {
        String query = "SELECT * FROM messages ORDER BY id ASC LIMIT ?";
        List<Message> drivers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                drivers.add(parseMessage(resultSet));
            }
            return drivers;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't get a limit list of messages from database.",
                    e);
        }
    }

    private Message parseMessage(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getObject("id", Long.class);
        String userName = resultSet.getString("username");
        String messageText = resultSet.getString("content");
        String sendTime = resultSet.getString("send_time");
        return new Message(id, userName, messageText, sendTime);
    }
}
