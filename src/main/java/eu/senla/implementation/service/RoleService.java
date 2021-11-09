package eu.senla.implementation.service;

import eu.senla.abstraction.dao.RoleRepositoryInterface;
import eu.senla.abstraction.service.RoleServiceInterface;
import eu.senla.constants.ServiceError;
import eu.senla.domain.RoleEntity;
import eu.senla.dto.RoleDTO;
import eu.senla.mapper.RoleMapper;
import eu.senla.tools.Result;
import eu.senla.tools.StatusType;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements RoleServiceInterface {
    private final RoleRepositoryInterface roleRepository;

    public RoleService(RoleRepositoryInterface roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Result create(RoleDTO role) {
        RoleEntity roleEntity = RoleMapper.INSTANCE.map(role);
        boolean result = roleRepository.add(roleEntity);
        if (!result) {
            return new Result(StatusType.Error, ServiceError.CREATED_ERROR_MSG);
        }
        return new Result(StatusType.Success, ServiceError.CREATED_SUCCESS_MSG);
    }

    @Override
    public Result update(RoleDTO role) {
        RoleEntity roleEntity = RoleMapper.INSTANCE.map(role);
        boolean result = roleRepository.update(roleEntity);
        if (!result) {
            return new Result(StatusType.Error,ServiceError.UPDATED_ERROR_MSG);
        }
        return new Result(StatusType.Success, ServiceError.UPDATED_SUCCESS_MSG);
    }

    @Override
    public Result remove(Long id) {
        boolean result = roleRepository.remove(id);
        if (!result) {
            return new Result(StatusType.Error, ServiceError.REMOVED_ERROR_MSG);
        }
        return new Result(StatusType.Success, ServiceError.REMOVED_SUCCESS_MSG);
    }

    @Override
    public RoleDTO findById(Long id) {
        RoleEntity roleEntity = roleRepository.findById(id);
        if (roleEntity == null) {
            return null;
        }
        return RoleMapper.INSTANCE.map(roleEntity);
    }
}
