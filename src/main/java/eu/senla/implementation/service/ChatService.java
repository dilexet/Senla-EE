package eu.senla.implementation.service;

import eu.senla.abstraction.dao.ChatRepositoryInterface;
import eu.senla.abstraction.service.ChatServiceInterface;
import eu.senla.domain.ChatEntity;
import eu.senla.dto.ChatDTO;
import eu.senla.mapper.ChatMapper;
import org.springframework.stereotype.Service;

@Service
public class ChatService implements ChatServiceInterface {
    private final ChatRepositoryInterface chatRepository;

    public ChatService(ChatRepositoryInterface chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public String create(ChatDTO chat) {
        ChatEntity chatEntity = ChatMapper.INSTANCE.map(chat);
        boolean result = chatRepository.add(chatEntity);
        if (!result) {
            return "Error";
        }
        return "Success";
    }

    @Override
    public String update(ChatDTO chat) {
        ChatEntity chatEntity = ChatMapper.INSTANCE.map(chat);
        boolean result = chatRepository.update(chatEntity);
        if (!result) {
            return "Error";
        }
        return "Success";
    }

    @Override
    public String remove(Long id) {
        boolean result = chatRepository.remove(id);
        if (!result) {
            return "Error";
        }
        return "Success";
    }

    @Override
    public ChatDTO find_by_id(Long id) {
        ChatEntity chatEntity = chatRepository.find_by_id(id);
        if (chatEntity == null) {
            return null;
        }
        return ChatMapper.INSTANCE.map(chatEntity);
    }
}
