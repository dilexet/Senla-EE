package eu.senla.implementation.service;

import eu.senla.abstraction.dao.MessageRepositoryInterface;
import eu.senla.abstraction.service.MessageServiceInterface;
import eu.senla.annotation.Transaction;
import eu.senla.constants.ServiceError;
import eu.senla.domain.MessageEntity;
import eu.senla.dto.MessageDTO;
import eu.senla.mapper.MessageMapper;
import eu.senla.tools.Result;
import eu.senla.tools.StatusType;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements MessageServiceInterface {
    private final MessageRepositoryInterface messageRepository;
    private final String SERVICE_NAME = "message";

    public MessageService(MessageRepositoryInterface messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    @Transaction
    public Result create(MessageDTO message) {
        MessageEntity messageEntity = MessageMapper.INSTANCE.map(message);
        boolean result = messageRepository.add(messageEntity);
        if (!result) {
            return new Result(StatusType.Error, String.format(ServiceError.CREATED_ERROR_MSG, SERVICE_NAME));
        }
        return new Result(StatusType.Success, String.format(ServiceError.CREATED_SUCCESS_MSG, SERVICE_NAME));
    }

    @Override
    @Transaction
    public Result update(MessageDTO message) {
        MessageEntity messageEntity = MessageMapper.INSTANCE.map(message);
        boolean result = messageRepository.update(messageEntity);
        if (!result) {
            return new Result(StatusType.Error, String.format(ServiceError.UPDATED_ERROR_MSG, SERVICE_NAME));
        }
        return new Result(StatusType.Success, String.format(ServiceError.UPDATED_SUCCESS_MSG, SERVICE_NAME));
    }

    @Override
    @Transaction
    public Result remove(Long id) {
        boolean result = messageRepository.remove(id);
        if (!result) {
            return new Result(StatusType.Error, String.format(ServiceError.REMOVED_ERROR_MSG, SERVICE_NAME));
        }
        return new Result(StatusType.Success, String.format(ServiceError.REMOVED_SUCCESS_MSG, SERVICE_NAME));
    }

    @Override
    @Transaction
    public MessageDTO findById(Long id) {
        MessageEntity messageEntity = messageRepository.findById(id);
        if (messageEntity == null) {
            return null;
        }
        return MessageMapper.INSTANCE.map(messageEntity);
    }
}
