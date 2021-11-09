package eu.senla.abstraction.dao;

import eu.senla.domain.UserEntity;

public interface UserRepositoryInterface {
    boolean add(UserEntity user);

    boolean update(UserEntity user);

    boolean remove(Long id);

    UserEntity findById(Long id);
}
