package eu.senla.implementation.service;

import eu.senla.abstraction.dao.MessageRepositoryInterface;
import eu.senla.abstraction.service.MessageServiceInterface;
import eu.senla.domain.MessageEntity;
import eu.senla.dto.MessageDTO;
import eu.senla.mapper.MessageMapper;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements MessageServiceInterface {
    private final MessageRepositoryInterface messageRepository;

    public MessageService(MessageRepositoryInterface messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public String create(MessageDTO message) {
        MessageEntity messageEntity = MessageMapper.INSTANCE.map(message);
        boolean result = messageRepository.add(messageEntity);
        if (!result) {
            return "Error";
        }
        return "Success";
    }

    @Override
    public String update(MessageDTO message) {
        MessageEntity messageEntity = MessageMapper.INSTANCE.map(message);
        boolean result = messageRepository.update(messageEntity);
        if (!result) {
            return "Error";
        }
        return "Success";
    }

    @Override
    public String remove(Long id) {
        boolean result = messageRepository.remove(id);
        if (!result) {
            return "Error";
        }
        return "Success";
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
