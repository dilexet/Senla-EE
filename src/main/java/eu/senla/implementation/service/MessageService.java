package eu.senla.implementation.service;

import eu.senla.abstraction.dao.MessageRepositoryInterface;
import eu.senla.abstraction.service.MessageServiceInterface;
import eu.senla.domain.MessageEntity;
import eu.senla.dto.MessageDTO;
import eu.senla.mapper.MessageMapper;
import eu.senla.tools.Result;
import eu.senla.tools.StatusType;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements MessageServiceInterface {
    private final String CREATED_SUCCESS_MSG = "Creation 'message' completed successfully";
    private final String UPDATED_SUCCESS_MSG = "Updated 'message' completed successfully";
    private final String REMOVED_SUCCESS_MSG = "Deletion 'message' completed successfully";

    private final String CREATED_ERROR_MSG = "Creation 'message' completed with error";
    private final String UPDATED_ERROR_MSG = "Updated 'message' completed with error";
    private final String REMOVED_ERROR_MSG = "Deletion 'message' completed with error";

    private final MessageRepositoryInterface messageRepository;

    public MessageService(MessageRepositoryInterface messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Result create(MessageDTO message) {
        MessageEntity messageEntity = MessageMapper.INSTANCE.map(message);
        boolean result = messageRepository.add(messageEntity);
        if (!result) {
            return new Result(StatusType.Error, CREATED_ERROR_MSG);
        }
        return new Result(StatusType.Success, CREATED_SUCCESS_MSG);
    }

    @Override
    public Result update(MessageDTO message) {
        MessageEntity messageEntity = MessageMapper.INSTANCE.map(message);
        boolean result = messageRepository.update(messageEntity);
        if (!result) {
            return new Result(StatusType.Error, UPDATED_ERROR_MSG);
        }
        return new Result(StatusType.Success, UPDATED_SUCCESS_MSG);
    }

    @Override
    public Result remove(Long id) {
        boolean result = messageRepository.remove(id);
        if (!result) {
            return new Result(StatusType.Error, REMOVED_ERROR_MSG);
        }
        return new Result(StatusType.Success, REMOVED_SUCCESS_MSG);
    }

    @Override
    public MessageDTO find_by_id(Long id) {
        MessageEntity messageEntity = messageRepository.find_by_id(id);
        if (messageEntity == null) {
            return null;
        }
        return MessageMapper.INSTANCE.map(messageEntity);
    }
}
