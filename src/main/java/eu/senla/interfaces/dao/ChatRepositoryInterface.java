package eu.senla.interfaces.dao;

import eu.senla.domain.Chat;

public interface ChatRepositoryInterface {
    boolean add(Chat chat);

    boolean update(Chat chat);

    boolean remove(Long id);

    Chat findById(Long id);
}
