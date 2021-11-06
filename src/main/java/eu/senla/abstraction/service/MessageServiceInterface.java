package eu.senla.abstraction.service;

import eu.senla.dto.MessageDTO;

public interface MessageServiceInterface {
    String create(MessageDTO message);

    String update(MessageDTO message);

    String remove(Long id);

    MessageDTO find_by_id(Long id);
}
