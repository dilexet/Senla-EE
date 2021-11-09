package eu.senla.implementation.dao;

import eu.senla.abstraction.dao.UserRepositoryInterface;
import eu.senla.domain.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Repository
public class UserRepository implements UserRepositoryInterface {
    private final List<UserEntity> users = new ArrayList<>();

    @Override
    public boolean add(UserEntity user) {
        user.setId(getNewIndex());
        users.add(user);
        return true;
    }

    @Override
    public boolean update(UserEntity user) {
        UserEntity existUser = findById(user.getId());
        if (existUser == null) {
            return false;
        }
        existUser.setName(user.getName());

        existUser.setRoles(user.getRoles());
        existUser.setEvents(user.getEvents());
        existUser.setChats(user.getChats());
        return true;
    }

    @Override
    public boolean remove(Long id) {
        UserEntity user = findById(id);
        if (user == null) {
            return false;
        }
        users.remove(user);
        return true;
    }

    @Override
    public UserEntity findById(Long id) {
        return users.stream().filter(x -> Objects.equals(x.getId(), id)).findFirst().orElse(null);
    }

    private Long getNewIndex() {
        UserEntity user = users.stream().max(Comparator.comparing(UserEntity::getId)).orElse(null);
        return user == null ? 1 : user.getId() + 1;
    }
}
