package eu.senla.abstraction.service;

import eu.senla.dto.UserDTO;

public interface UserServiceInterface {
    String create(UserDTO user);

    String update(UserDTO user);

    String remove(Long id);

    UserDTO find_by_id(Long id);
}
