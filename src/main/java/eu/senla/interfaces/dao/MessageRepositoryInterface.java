package eu.senla.interfaces.dao;

import eu.senla.domain.Message;

public interface MessageRepositoryInterface {
    boolean add(Message message);

    boolean update(Message message);

    boolean remove(Long id);

    Message findById(Long id);
}
