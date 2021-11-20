package eu.senla.implementation.dao;

import eu.senla.interfaces.dao.RoleRepositoryInterface;
import eu.senla.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Repository
public class RoleRepository implements RoleRepositoryInterface {
    private final List<Role> roles = new ArrayList<>();

    @Override
    public boolean add(Role role) {
        role.setId(getNewIndex());
        roles.add(role);
        return true;
    }

    @Override
    public boolean update(Role role) {
        Role existRole = findById(role.getId());
        if (existRole == null) {
            return false;
        }
        existRole.setName(role.getName());

        existRole.setUser(role.getUser());
        existRole.setEvent(role.getEvent());
        return true;
    }

    @Override
    public boolean remove(Long id) {
        Role role = findById(id);
        if (role == null) {
            return false;
        }
        roles.remove(role);
        return true;
    }

    @Override
    public Role findById(Long id) {
        return roles.stream().filter(x -> Objects.equals(x.getId(), id)).findFirst().orElse(null);
    }

    private Long getNewIndex() {
        Role role = roles.stream().max(Comparator.comparing(Role::getId)).orElse(null);
        return role == null ? 1 : role.getId() + 1;
    }
}
