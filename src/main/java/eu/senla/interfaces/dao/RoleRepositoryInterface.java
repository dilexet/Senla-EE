package eu.senla.interfaces.dao;

import eu.senla.domain.Role;

public interface RoleRepositoryInterface {
    boolean add(Role role);

    boolean update(Role role);

    boolean remove(Long id);

    Role findById(Long id);
}
