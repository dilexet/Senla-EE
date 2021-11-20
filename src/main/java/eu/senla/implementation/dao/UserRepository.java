package eu.senla.implementation.dao;

import eu.senla.interfaces.dao.UserRepositoryInterface;
import eu.senla.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Repository
public class UserRepository implements UserRepositoryInterface {
    private final List<User> users = new ArrayList<>();

    @Override
    public boolean add(User user) {
        user.setId(getNewIndex());
        users.add(user);
        return true;
    }

    @Override
    public boolean update(User user) {
        User existUser = findById(user.getId());
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
        User user = findById(id);
        if (user == null) {
            return false;
        }
        users.remove(user);
        return true;
    }

    @Override
    public User findById(Long id) {
        return users.stream().filter(x -> Objects.equals(x.getId(), id)).findFirst().orElse(null);
    }

    private Long getNewIndex() {
        User user = users.stream().max(Comparator.comparing(User::getId)).orElse(null);
        return user == null ? 1 : user.getId() + 1;
    }
}
