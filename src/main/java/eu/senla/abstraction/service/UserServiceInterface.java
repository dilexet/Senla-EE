package eu.senla.abstraction.service;

import eu.senla.dto.UserDTO;
import eu.senla.tools.Result;

public interface UserServiceInterface {
    Result create(UserDTO user);

    Result update(UserDTO user);

    Result remove(Long id);

    UserDTO findById(Long id);
}
