package service.impl;

import java.util.List;
import dao.MessageDao;
import dao.impl.MessageDaoImpl;
import model.Message;
import service.MessageService;

public class MessageServiceImpl implements MessageService {
    private final MessageDao messageDao = new MessageDaoImpl();

    @Override
    public List<Message> getLimitAsc(int limit) {
        return messageDao.getLimitAsc(limit);
    }

    @Override
    public Message send(Message message) {
        return messageDao.save(message);
    }
}
