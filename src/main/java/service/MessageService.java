package service;

import java.util.List;
import model.Message;

public interface MessageService {
    List<Message> getLimitAsc(int limit);

    Message send(Message message);
}
