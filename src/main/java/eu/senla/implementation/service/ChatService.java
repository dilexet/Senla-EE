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
    private final String SERVICE_NAME = "chat";

    public ChatService(ChatRepositoryInterface chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public Result create(ChatDTO chat) {
        ChatEntity chatEntity = ChatMapper.INSTANCE.map(chat);
        boolean result = chatRepository.add(chatEntity);
        if (!result) {
            return new Result(StatusType.Error, String.format(ServiceError.CREATED_ERROR_MSG, SERVICE_NAME));
        }
        return new Result(StatusType.Success, String.format(ServiceError.CREATED_SUCCESS_MSG, SERVICE_NAME));
    }

    @Override
    public Result update(ChatDTO chat) {
        ChatEntity chatEntity = ChatMapper.INSTANCE.map(chat);
        boolean result = chatRepository.update(chatEntity);
        if (!result) {
            return new Result(StatusType.Error, String.format(ServiceError.UPDATED_ERROR_MSG, SERVICE_NAME));
        }
        return new Result(StatusType.Success, String.format(ServiceError.UPDATED_SUCCESS_MSG, SERVICE_NAME));
    }

    @Override
    public Result remove(Long id) {
        boolean result = chatRepository.remove(id);
        if (!result) {
            return new Result(StatusType.Error, String.format(ServiceError.REMOVED_ERROR_MSG, SERVICE_NAME));
        }
        return new Result(StatusType.Success, String.format(ServiceError.REMOVED_SUCCESS_MSG, SERVICE_NAME));
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
