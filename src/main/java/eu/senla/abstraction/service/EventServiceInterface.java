package eu.senla.abstraction.service;

import eu.senla.dto.EventDTO;

public interface EventServiceInterface {
    String create(EventDTO event);

    String update(EventDTO event);

    String remove(Long id);

    EventDTO find_by_id(Long id);
}
