package eu.senla.abstraction.service;

import eu.senla.dto.RoleDTO;
import eu.senla.tools.Result;

public interface RoleServiceInterface {
    Result create(RoleDTO role);

    Result update(RoleDTO role);

    Result remove(Long id);

    RoleDTO findById(Long id);
}
