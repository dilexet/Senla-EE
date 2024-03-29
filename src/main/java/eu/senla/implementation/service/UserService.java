package eu.senla.implementation.service;

import eu.senla.abstraction.dao.UserRepositoryInterface;
import eu.senla.abstraction.service.UserServiceInterface;
import eu.senla.constants.ServiceError;
import eu.senla.domain.UserEntity;
import eu.senla.dto.UserDTO;
import eu.senla.mapper.UserMapper;
import eu.senla.tools.Result;
import eu.senla.tools.StatusType;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepositoryInterface userRepository;
    private final String SERVICE_NAME = "user";

    public UserService(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Result create(UserDTO user) {
        UserEntity userEntity = UserMapper.INSTANCE.map(user);
        boolean result = userRepository.add(userEntity);
        if (!result) {
            return new Result(StatusType.Error, String.format(ServiceError.CREATED_ERROR_MSG, SERVICE_NAME));
        }
        return new Result(StatusType.Success, String.format(ServiceError.CREATED_SUCCESS_MSG, SERVICE_NAME));
    }

    @Override
    public Result update(UserDTO user) {
        UserEntity userEntity = UserMapper.INSTANCE.map(user);
        boolean result = userRepository.update(userEntity);
        if (!result) {
            return new Result(StatusType.Error, String.format(ServiceError.UPDATED_ERROR_MSG, SERVICE_NAME));
        }
        return new Result(StatusType.Success, String.format(ServiceError.UPDATED_SUCCESS_MSG, SERVICE_NAME));
    }

    @Override
    public Result remove(Long id) {
        boolean result = userRepository.remove(id);
        if (!result) {
            return new Result(StatusType.Error, String.format(ServiceError.REMOVED_ERROR_MSG, SERVICE_NAME));
        }
        return new Result(StatusType.Success, String.format(ServiceError.REMOVED_SUCCESS_MSG, SERVICE_NAME));
    }

    @Override
    public UserDTO findById(Long id) {
        UserEntity userEntity = userRepository.findById(id);
        if (userEntity == null) {
            return null;
        }
        return UserMapper.INSTANCE.map(userEntity);
    }
}
