package eu.senla.implementation.service;

import eu.senla.abstraction.dao.ChatRepositoryInterface;
import eu.senla.abstraction.service.ChatServiceInterface;
import eu.senla.constants.ServiceError;
import eu.senla.domain.ChatEntity;
import eu.senla.dto.ChatDTO;
import eu.senla.mapper.ChatMapper;
import eu.senla.tools.Result;
import eu.senla.tools.StatusType;
import org.springframework.stereotype.Service;

@Service
public class ChatService implements ChatServiceInterface {
    private final ChatRepositoryInterface chatRepository;

    public ChatService(ChatRepositoryInterface chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public Result create(ChatDTO chat) {
        ChatEntity chatEntity = ChatMapper.INSTANCE.map(chat);
        boolean result = chatRepository.add(chatEntity);
        if (!result) {
            return new Result(StatusType.Error, ServiceError.CREATED_ERROR_MSG);
        }
        return new Result(StatusType.Success, ServiceError.CREATED_SUCCESS_MSG);
    }

    @Override
    public Result update(ChatDTO chat) {
        ChatEntity chatEntity = ChatMapper.INSTANCE.map(chat);
        boolean result = chatRepository.update(chatEntity);
        if (!result) {
            return new Result(StatusType.Error, ServiceError.UPDATED_ERROR_MSG);
        }
        return new Result(StatusType.Success, ServiceError.UPDATED_SUCCESS_MSG);
    }

    @Override
    public Result remove(Long id) {
        boolean result = chatRepository.remove(id);
        if (!result) {
            return new Result(StatusType.Error, ServiceError.REMOVED_ERROR_MSG);
        }
        return new Result(StatusType.Success, ServiceError.REMOVED_SUCCESS_MSG);
    }

    @Override
    public ChatDTO findById(Long id) {
        ChatEntity chatEntity = chatRepository.findById(id);
        if (chatEntity == null) {
            return null;
        }
        return ChatMapper.INSTANCE.map(chatEntity);
    }
}
