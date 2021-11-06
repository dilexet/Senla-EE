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
        try {
            user.setId(getNewIndex());
            users.add(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(UserEntity user) {
        UserEntity existUser = find_by_id(user.getId());
        if (existUser == null) {
            return false;
        }
        try {
            existUser.setName(user.getName());

            existUser.setRoles(user.getRoles());
            existUser.setEvents(user.getEvents());
            existUser.setChats(user.getChats());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(Long id) {
        UserEntity user = find_by_id(id);
        if (user == null) {
            return false;
        }
        try {
            users.remove(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public UserEntity find_by_id(Long id) {
        return users.stream().filter(x -> Objects.equals(x.getId(), id)).findFirst().orElse(null);
    }

    private Long getNewIndex() {
        UserEntity user = users.stream().max(Comparator.comparing(UserEntity::getId)).orElse(null);
        return user == null ? 1 : user.getId() + 1;
    }
}
