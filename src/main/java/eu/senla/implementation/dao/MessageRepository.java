package eu.senla.implementation.dao;

import eu.senla.abstraction.dao.MessageRepositoryInterface;
import eu.senla.domain.MessageEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Repository
public class MessageRepository implements MessageRepositoryInterface {
    private final List<MessageEntity> messages = new ArrayList<>();

    @Override
    public boolean add(MessageEntity message) {
        try {
            message.setId(getNewIndex());
            messages.add(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(MessageEntity message) {
        MessageEntity existMessage = find_by_id(message.getId());
        if (existMessage == null) {
            return false;
        }
        try {
            existMessage.setText(message.getText());
            existMessage.setSend_Date(message.getSend_Date());

            existMessage.setUser(message.getUser());
            existMessage.setChat(message.getChat());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(Long id) {
        MessageEntity message = find_by_id(id);
        if (message == null) {
            return false;
        }
        try {
            messages.remove(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public MessageEntity find_by_id(Long id) {
        return messages.stream().filter(x -> Objects.equals(x.getId(), id)).findFirst().orElse(null);
    }

    private Long getNewIndex() {
        MessageEntity message = messages.stream().max(Comparator.comparing(MessageEntity::getId)).orElse(null);
        return message == null ? 1 : message.getId() + 1;
    }
}
