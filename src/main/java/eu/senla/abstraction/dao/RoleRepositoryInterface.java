package eu.senla.abstraction.dao;

import eu.senla.domain.RoleEntity;

public interface RoleRepositoryInterface {
    boolean add(RoleEntity role);

    boolean update(RoleEntity role);

    boolean remove(Long id);

    RoleEntity find_by_id(Long id);
}
