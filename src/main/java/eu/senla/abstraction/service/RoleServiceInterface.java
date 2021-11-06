package eu.senla.abstraction.service;

import eu.senla.dto.RoleDTO;

public interface RoleServiceInterface {
    String create(RoleDTO role);

    String update(RoleDTO role);

    String remove(Long id);

    RoleDTO find_by_id(Long id);
}
