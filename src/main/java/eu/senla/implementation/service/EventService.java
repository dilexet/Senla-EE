package eu.senla.implementation.service;

import eu.senla.abstraction.dao.EventRepositoryInterface;
import eu.senla.abstraction.service.EventServiceInterface;
import eu.senla.domain.EventEntity;
import eu.senla.dto.EventDTO;
import eu.senla.mapper.EventMapper;
import org.springframework.stereotype.Service;

@Service
public class EventService implements EventServiceInterface {
    private final EventRepositoryInterface eventRepository;

    public EventService(EventRepositoryInterface eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public String create(EventDTO event) {
        EventEntity eventEntity = EventMapper.INSTANCE.map(event);
        boolean result = eventRepository.add(eventEntity);
        if (!result) {
            return "Error";
        }
        return "Success";
    }

    @Override
    public String update(EventDTO event) {
        EventEntity eventEntity = EventMapper.INSTANCE.map(event);
        boolean result = eventRepository.update(eventEntity);
        if (!result) {
            return "Error";
        }
        return "Success";
    }

    @Override
    public String remove(Long id) {
        boolean result = eventRepository.remove(id);
        if (!result) {
            return "Error";
        }
        return "Success";
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
