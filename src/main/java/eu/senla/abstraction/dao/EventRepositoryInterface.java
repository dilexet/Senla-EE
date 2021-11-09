package eu.senla.abstraction.dao;

import eu.senla.domain.EventEntity;

public interface EventRepositoryInterface {
    boolean add(EventEntity event);

    boolean update(EventEntity event);

    boolean remove(Long id);

    EventEntity findById(Long id);
}
