package eu.senla.abstraction.service;

import eu.senla.dto.MessageDTO;
import eu.senla.tools.Result;

public interface MessageServiceInterface {
    Result create(MessageDTO message);

    Result update(MessageDTO message);

    Result remove(Long id);

    MessageDTO find_by_id(Long id);
}
