package eu.senla.implementation.dao;

import eu.senla.abstraction.dao.RoleRepositoryInterface;
import eu.senla.domain.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Repository
public class RoleRepository implements RoleRepositoryInterface {
    private final List<RoleEntity> roles = new ArrayList<>();

    @Override
    public boolean add(RoleEntity role) {
        try {
            role.setId(getNewIndex());
            roles.add(role);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(RoleEntity role) {
        RoleEntity existRole = find_by_id(role.getId());
        if (existRole == null) {
            return false;
        }
        try {
            existRole.setName(role.getName());

            existRole.setUser(role.getUser());
            existRole.setEvent(role.getEvent());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(Long id) {
        RoleEntity role = find_by_id(id);
        if (role == null) {
            return false;
        }
        try {
            roles.remove(role);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public RoleEntity find_by_id(Long id) {
        return roles.stream().filter(x -> Objects.equals(x.getId(), id)).findFirst().orElse(null);
    }

    private Long getNewIndex() {
        RoleEntity role = roles.stream().max(Comparator.comparing(RoleEntity::getId)).orElse(null);
        return role == null ? 1 : role.getId() + 1;
    }
}
