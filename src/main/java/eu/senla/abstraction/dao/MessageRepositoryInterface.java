package eu.senla.abstraction.dao;

import eu.senla.domain.MessageEntity;

public interface MessageRepositoryInterface {
    boolean add(MessageEntity message);

    boolean update(MessageEntity message);

    boolean remove(Long id);

    MessageEntity findById(Long id);
}
