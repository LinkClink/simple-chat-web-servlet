package dao;

import java.util.List;
import model.Message;

public interface MessageDao extends GenericDao<Message> {
    List<Message> getLimitAsc(int limit);
}
