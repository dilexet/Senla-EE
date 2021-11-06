package eu.senla.abstraction.service;

import eu.senla.dto.ChatDTO;

public interface ChatServiceInterface {
    String create(ChatDTO chat);

    String update(ChatDTO chat);

    String remove(Long id);

    ChatDTO find_by_id(Long id);
}
