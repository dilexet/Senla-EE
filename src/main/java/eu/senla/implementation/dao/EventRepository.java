package eu.senla.implementation.dao;

import eu.senla.interfaces.dao.EventRepositoryInterface;
import eu.senla.domain.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Repository
public class EventRepository implements EventRepositoryInterface {
    private final List<Event> events = new ArrayList<>();

    @Override
    public boolean add(Event event) {
        event.setId(getNewIndex());
        events.add(event);
        return true;
    }

    @Override
    public boolean update(Event event) {
        Event existEvent = findById(event.getId());
        if (existEvent == null) {
            return false;
        }

        existEvent.setName(event.getName());
        existEvent.setDescription(event.getDescription());
        existEvent.setStartDate(event.getStartDate());

        existEvent.setRoles(event.getRoles());
        existEvent.setUsers(event.getUsers());
        existEvent.setChat(event.getChat());

        return true;
    }

    @Override
    public boolean remove(Long id) {
        Event event = findById(id);
        if (event == null) {
            return false;
        }

        events.remove(event);

        return true;
    }

    @Override
    public Event findById(Long id) {
        return events.stream().filter(x -> Objects.equals(x.getId(), id)).findFirst().orElse(null);
    }

    private Long getNewIndex() {
        Event event = events.stream().max(Comparator.comparing(Event::getId)).orElse(null);
        return event == null ? 1 : event.getId() + 1;
    }
}
