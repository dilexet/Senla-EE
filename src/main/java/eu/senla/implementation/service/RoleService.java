package eu.senla.implementation.service;

import eu.senla.abstraction.dao.RoleRepositoryInterface;
import eu.senla.abstraction.service.RoleServiceInterface;
import eu.senla.domain.RoleEntity;
import eu.senla.dto.RoleDTO;
import eu.senla.mapper.RoleMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements RoleServiceInterface {
    private final RoleRepositoryInterface roleRepository;

    public RoleService(RoleRepositoryInterface roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public String create(RoleDTO role) {
        RoleEntity roleEntity = RoleMapper.INSTANCE.map(role);
        boolean result = roleRepository.add(roleEntity);
        if (!result) {
            return "Error";
        }
        return "Success";
    }

    @Override
    public String update(RoleDTO role) {
        RoleEntity roleEntity = RoleMapper.INSTANCE.map(role);
        boolean result = roleRepository.update(roleEntity);
        if (!result) {
            return "Error";
        }
        return "Success";
    }

    @Override
    public String remove(Long id) {
        boolean result = roleRepository.remove(id);
        if (!result) {
            return "Error";
        }
        return "Success";
    }

    @Override
    public RoleDTO find_by_id(Long id) {
        RoleEntity roleEntity = roleRepository.find_by_id(id);
        if (roleEntity == null) {
            return null;
        }
        return RoleMapper.INSTANCE.map(roleEntity);
    }
}
