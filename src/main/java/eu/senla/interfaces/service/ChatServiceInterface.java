package eu.senla.interfaces.service;

import eu.senla.dto.ChatDTO;
import eu.senla.tools.Result;

public interface ChatServiceInterface {
    Result create(ChatDTO chat);

    Result update(ChatDTO chat);

    Result remove(Long id);

    ChatDTO findById(Long id);
}
