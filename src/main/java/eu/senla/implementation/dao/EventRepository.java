package eu.senla.implementation.dao;

import eu.senla.abstraction.dao.EventRepositoryInterface;
import eu.senla.domain.EventEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Repository
public class EventRepository implements EventRepositoryInterface {
    private final List<EventEntity> events = new ArrayList<>();

    @Override
    public boolean add(EventEntity event) {
        try {
            event.setId(getNewIndex());
            events.add(event);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(EventEntity event) {
        EventEntity existEvent = find_by_id(event.getId());
        if (existEvent == null) {
            return false;
        }
        try {
            existEvent.setName(event.getName());
            existEvent.setDescription(event.getDescription());
            existEvent.setStart_Date(event.getStart_Date());

            existEvent.setRoles(event.getRoles());
            existEvent.setUsers(event.getUsers());
            existEvent.setChat(event.getChat());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(Long id) {
        EventEntity event = find_by_id(id);
        if (event == null) {
            return false;
        }
        try {
            events.remove(event);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public EventEntity find_by_id(Long id) {
        return events.stream().filter(x -> Objects.equals(x.getId(), id)).findFirst().orElse(null);
    }

    private Long getNewIndex() {
        EventEntity event = events.stream().max(Comparator.comparing(EventEntity::getId)).orElse(null);
        return event == null ? 1 : event.getId() + 1;
    }
}
