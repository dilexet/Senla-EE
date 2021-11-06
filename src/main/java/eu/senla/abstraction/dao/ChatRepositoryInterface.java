package eu.senla.abstraction.dao;

import eu.senla.domain.ChatEntity;

public interface ChatRepositoryInterface {
    boolean add(ChatEntity chat);

    boolean update(ChatEntity chat);

    boolean remove(Long id);

    ChatEntity find_by_id(Long id);
}
