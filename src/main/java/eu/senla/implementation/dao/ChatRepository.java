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
        try {
            chat.setId(getNewIndex());
            chats.add(chat);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(ChatEntity chat) {
        ChatEntity existChat = find_by_id(chat.getId());
        if (existChat == null) {
            return false;
        }
        try {
            existChat.setName(chat.getName());

            existChat.setMessages(chat.getMessages());
            existChat.setEvent(chat.getEvent());
            existChat.setUsers(chat.getUsers());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(Long id) {
        ChatEntity chat = find_by_id(id);
        if (chat == null) {
            return false;
        }
        try {
            chats.remove(chat);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public ChatEntity find_by_id(Long id) {
        return chats.stream().filter(x -> Objects.equals(x.getId(), id)).findFirst().orElse(null);
    }

    private Long getNewIndex() {
        ChatEntity chat = chats.stream().max(Comparator.comparing(ChatEntity::getId)).orElse(null);
        return chat == null ? 1 : chat.getId() + 1;
    }
}
