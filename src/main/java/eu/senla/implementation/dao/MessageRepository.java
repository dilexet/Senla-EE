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

        message.setId(getNewIndex());
        messages.add(message);

        return true;
    }

    @Override
    public boolean update(MessageEntity message) {
        MessageEntity existMessage = findById(message.getId());
        if (existMessage == null) {
            return false;
        }

        existMessage.setText(message.getText());
        existMessage.setSendDate(message.getSendDate());

        existMessage.setUser(message.getUser());
        existMessage.setChat(message.getChat());

        return true;
    }

    @Override
    public boolean remove(Long id) {
        MessageEntity message = findById(id);
        if (message == null) {
            return false;
        }

        messages.remove(message);

        return true;
    }

    @Override
    public MessageEntity findById(Long id) {
        return messages.stream().filter(x -> Objects.equals(x.getId(), id)).findFirst().orElse(null);
    }

    private Long getNewIndex() {
        MessageEntity message = messages.stream().max(Comparator.comparing(MessageEntity::getId)).orElse(null);
        return message == null ? 1 : message.getId() + 1;
    }
}
