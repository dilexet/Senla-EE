package eu.senla.implementation.service;

import eu.senla.abstraction.dao.ChatRepositoryInterface;
import eu.senla.abstraction.service.ChatServiceInterface;
import eu.senla.domain.ChatEntity;
import eu.senla.dto.ChatDTO;
import eu.senla.mapper.ChatMapper;
import eu.senla.tools.Result;
import eu.senla.tools.StatusType;
import org.springframework.stereotype.Service;

@Service
public class ChatService implements ChatServiceInterface {
    private final String CREATED_SUCCESS_MSG = "Creation 'chat' completed successfully";
    private final String UPDATED_SUCCESS_MSG = "Updated 'chat' completed successfully";
    private final String REMOVED_SUCCESS_MSG = "Deletion 'chat' completed successfully";

    private final String CREATED_ERROR_MSG = "Creation 'chat' completed with error";
    private final String UPDATED_ERROR_MSG = "Updated 'chat' completed with error";
    private final String REMOVED_ERROR_MSG = "Deletion 'chat' completed with error";

    private final ChatRepositoryInterface chatRepository;

    public ChatService(ChatRepositoryInterface chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public Result create(ChatDTO chat) {
        ChatEntity chatEntity = ChatMapper.INSTANCE.map(chat);
        boolean result = chatRepository.add(chatEntity);
        if (!result) {
            return new Result(StatusType.Error, CREATED_ERROR_MSG);
        }
        return new Result(StatusType.Success, CREATED_SUCCESS_MSG);
    }

    @Override
    public Result update(ChatDTO chat) {
        ChatEntity chatEntity = ChatMapper.INSTANCE.map(chat);
        boolean result = chatRepository.update(chatEntity);
        if (!result) {
            return new Result(StatusType.Error, UPDATED_ERROR_MSG);
        }
        return new Result(StatusType.Success, UPDATED_SUCCESS_MSG);
    }

    @Override
    public Result remove(Long id) {
        boolean result = chatRepository.remove(id);
        if (!result) {
            return new Result(StatusType.Error, REMOVED_ERROR_MSG);
        }
        return new Result(StatusType.Success, REMOVED_SUCCESS_MSG);
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
