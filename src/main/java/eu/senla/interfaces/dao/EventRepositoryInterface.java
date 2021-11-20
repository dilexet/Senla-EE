package eu.senla.interfaces.dao;

import eu.senla.domain.Event;

public interface EventRepositoryInterface {
    boolean add(Event event);

    boolean update(Event event);

    boolean remove(Long id);

    Event findById(Long id);
}
