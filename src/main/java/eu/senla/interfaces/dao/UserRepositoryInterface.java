package eu.senla.interfaces.dao;

import eu.senla.domain.User;

public interface UserRepositoryInterface {
    boolean add(User user);

    boolean update(User user);

    boolean remove(Long id);

    User findById(Long id);
}
