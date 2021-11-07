package eu.senla.implementation.service;

import eu.senla.abstraction.dao.EventRepositoryInterface;
import eu.senla.abstraction.service.EventServiceInterface;
import eu.senla.domain.EventEntity;
import eu.senla.dto.EventDTO;
import eu.senla.mapper.EventMapper;
import eu.senla.tools.Result;
import eu.senla.tools.StatusType;
import org.springframework.stereotype.Service;

@Service
public class EventService implements EventServiceInterface {
    private final String CREATED_SUCCESS_MSG = "Creation 'event' completed successfully";
    private final String UPDATED_SUCCESS_MSG = "Updated 'event' completed successfully";
    private final String REMOVED_SUCCESS_MSG = "Deletion 'event' completed successfully";

    private final String CREATED_ERROR_MSG = "Creation 'event' completed with error";
    private final String UPDATED_ERROR_MSG = "Updated 'event' completed with error";
    private final String REMOVED_ERROR_MSG = "Deletion 'event' completed with error";

    private final EventRepositoryInterface eventRepository;

    public EventService(EventRepositoryInterface eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Result create(EventDTO event) {
        EventEntity eventEntity = EventMapper.INSTANCE.map(event);
        boolean result = eventRepository.add(eventEntity);
        if (!result) {
            return new Result(StatusType.Error, CREATED_ERROR_MSG);
        }
        return new Result(StatusType.Success, CREATED_SUCCESS_MSG);
    }

    @Override
    public Result update(EventDTO event) {
        EventEntity eventEntity = EventMapper.INSTANCE.map(event);
        boolean result = eventRepository.update(eventEntity);
        if (!result) {
            return new Result(StatusType.Error, UPDATED_ERROR_MSG);
        }
        return new Result(StatusType.Success, UPDATED_SUCCESS_MSG);
    }

    @Override
    public Result remove(Long id) {
        boolean result = eventRepository.remove(id);
        if (!result) {
            return new Result(StatusType.Error, REMOVED_ERROR_MSG);
        }
        return new Result(StatusType.Success, REMOVED_SUCCESS_MSG);
    }

    @Override
    public EventDTO find_by_id(Long id) {
        EventEntity eventEntity = eventRepository.find_by_id(id);
        if (eventEntity == null) {
            return null;
        }
        return EventMapper.INSTANCE.map(eventEntity);
    }
}
