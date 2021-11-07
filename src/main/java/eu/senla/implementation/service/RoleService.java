package eu.senla.implementation.service;

import eu.senla.abstraction.dao.RoleRepositoryInterface;
import eu.senla.abstraction.service.RoleServiceInterface;
import eu.senla.domain.RoleEntity;
import eu.senla.dto.RoleDTO;
import eu.senla.mapper.RoleMapper;
import eu.senla.tools.Result;
import eu.senla.tools.StatusType;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements RoleServiceInterface {
    private final String CREATED_SUCCESS_MSG = "Creation 'role' completed successfully";
    private final String UPDATED_SUCCESS_MSG = "Updated 'role' completed successfully";
    private final String REMOVED_SUCCESS_MSG = "Deletion 'role' completed successfully";

    private final String CREATED_ERROR_MSG = "Creation 'role' completed with error";
    private final String UPDATED_ERROR_MSG = "Updated 'role' completed with error";
    private final String REMOVED_ERROR_MSG = "Deletion 'role' completed with error";

    private final RoleRepositoryInterface roleRepository;

    public RoleService(RoleRepositoryInterface roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Result create(RoleDTO role) {
        RoleEntity roleEntity = RoleMapper.INSTANCE.map(role);
        boolean result = roleRepository.add(roleEntity);
        if (!result) {
            return new Result(StatusType.Error, CREATED_ERROR_MSG);
        }
        return new Result(StatusType.Success, CREATED_SUCCESS_MSG);
    }

    @Override
    public Result update(RoleDTO role) {
        RoleEntity roleEntity = RoleMapper.INSTANCE.map(role);
        boolean result = roleRepository.update(roleEntity);
        if (!result) {
            return new Result(StatusType.Error, UPDATED_ERROR_MSG);
        }
        return new Result(StatusType.Success, UPDATED_SUCCESS_MSG);
    }

    @Override
    public Result remove(Long id) {
        boolean result = roleRepository.remove(id);
        if (!result) {
            return new Result(StatusType.Error, REMOVED_ERROR_MSG);
        }
        return new Result(StatusType.Success, REMOVED_SUCCESS_MSG);
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
