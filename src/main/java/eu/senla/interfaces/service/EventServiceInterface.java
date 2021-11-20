package eu.senla.interfaces.service;

import eu.senla.dto.EventDTO;
import eu.senla.tools.Result;

public interface EventServiceInterface {
    Result create(EventDTO event);

    Result update(EventDTO event);

    Result remove(Long id);

    EventDTO findById(Long id);
}
