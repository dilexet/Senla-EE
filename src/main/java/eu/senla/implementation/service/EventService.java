package eu.senla.implementation.service;

import eu.senla.abstraction.dao.EventRepositoryInterface;
import eu.senla.abstraction.service.EventServiceInterface;
import eu.senla.constants.ServiceError;
import eu.senla.domain.EventEntity;
import eu.senla.dto.EventDTO;
import eu.senla.mapper.EventMapper;
import eu.senla.tools.Result;
import eu.senla.tools.StatusType;
import org.springframework.stereotype.Service;

@Service
public class EventService implements EventServiceInterface {
    private final EventRepositoryInterface eventRepository;
    private final String SERVICE_NAME = "event";

    public EventService(EventRepositoryInterface eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Result create(EventDTO event) {
        EventEntity eventEntity = EventMapper.INSTANCE.map(event);
        boolean result = eventRepository.add(eventEntity);
        if (!result) {
            return new Result(StatusType.Error, String.format(ServiceError.CREATED_ERROR_MSG, SERVICE_NAME));
        }
        return new Result(StatusType.Success, String.format(ServiceError.CREATED_SUCCESS_MSG, SERVICE_NAME));
    }

    @Override
    public Result update(EventDTO event) {
        EventEntity eventEntity = EventMapper.INSTANCE.map(event);
        boolean result = eventRepository.update(eventEntity);
        if (!result) {
            return new Result(StatusType.Error, String.format(ServiceError.UPDATED_ERROR_MSG, SERVICE_NAME));
        }
        return new Result(StatusType.Success, String.format(ServiceError.UPDATED_SUCCESS_MSG, SERVICE_NAME));
    }

    @Override
    public Result remove(Long id) {
        boolean result = eventRepository.remove(id);
        if (!result) {
            return new Result(StatusType.Error, String.format(ServiceError.REMOVED_ERROR_MSG, SERVICE_NAME));
        }
        return new Result(StatusType.Success, String.format(ServiceError.REMOVED_SUCCESS_MSG, SERVICE_NAME));
    }

    @Override
    public EventDTO findById(Long id) {
        EventEntity eventEntity = eventRepository.findById(id);
        if (eventEntity == null) {
            return null;
        }
        return EventMapper.INSTANCE.map(eventEntity);
    }
}
