package eu.senla.abstraction.dao;

import eu.senla.domain.MessageEntity;

public interface MessageRepositoryInterface {
    boolean add(MessageEntity message);

    boolean update(MessageEntity message);

    boolean remove(Long id);

    MessageEntity find_by_id(Long id);
}
