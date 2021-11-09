package eu.senla.implementation.dao;

import eu.senla.abstraction.dao.ChatRepositoryInterface;
import eu.senla.domain.ChatEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Repository
public class ChatRepository implements ChatRepositoryInterface {

    private final List<ChatEntity> chats = new ArrayList<>();

    @Override
    public boolean add(ChatEntity chat) {
        chat.setId(getNewIndex());
        chats.add(chat);

        return true;
    }

    @Override
    public boolean update(ChatEntity chat) {
        ChatEntity existChat = findById(chat.getId());
        if (existChat == null) {
            return false;
        }

        existChat.setName(chat.getName());
        existChat.setMessages(chat.getMessages());
        existChat.setEvent(chat.getEvent());
        existChat.setUsers(chat.getUsers());

        return true;
    }

    @Override
    public boolean remove(Long id) {
        ChatEntity chat = findById(id);
        if (chat == null) {
            return false;
        }

        chats.remove(chat);

        return true;
    }

    @Override
    public ChatEntity findById(Long id) {
        return chats.stream().filter(x -> Objects.equals(x.getId(), id)).findFirst().orElse(null);
    }

    private Long getNewIndex() {
        ChatEntity chat = chats.stream().max(Comparator.comparing(ChatEntity::getId)).orElse(null);
        return chat == null ? 1 : chat.getId() + 1;
    }
}
