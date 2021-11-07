package eu.senla.implementation.service;

import eu.senla.abstraction.dao.UserRepositoryInterface;
import eu.senla.abstraction.service.UserServiceInterface;
import eu.senla.domain.UserEntity;
import eu.senla.dto.UserDTO;
import eu.senla.mapper.UserMapper;
import eu.senla.tools.Result;
import eu.senla.tools.StatusType;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {
    private final String CREATED_SUCCESS_MSG = "Creation 'user' completed successfully";
    private final String UPDATED_SUCCESS_MSG = "Updated 'user' completed successfully";
    private final String REMOVED_SUCCESS_MSG = "Deletion 'user' completed successfully";

    private final String CREATED_ERROR_MSG = "Creation 'user' completed with error";
    private final String UPDATED_ERROR_MSG = "Updated 'user' completed with error";
    private final String REMOVED_ERROR_MSG = "Deletion 'user' completed with error";

    private final UserRepositoryInterface userRepository;

    public UserService(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Result create(UserDTO user) {
        UserEntity userEntity = UserMapper.INSTANCE.map(user);
        boolean result = userRepository.add(userEntity);
        if (!result) {
            return new Result(StatusType.Error, CREATED_ERROR_MSG);
        }
        return new Result(StatusType.Success, CREATED_SUCCESS_MSG);
    }

    @Override
    public Result update(UserDTO user) {
        UserEntity userEntity = UserMapper.INSTANCE.map(user);
        boolean result = userRepository.update(userEntity);
        if (!result) {
            return new Result(StatusType.Error, UPDATED_ERROR_MSG);
        }
        return new Result(StatusType.Success, UPDATED_SUCCESS_MSG);
    }

    @Override
    public Result remove(Long id) {
        boolean result = userRepository.remove(id);
        if (!result) {
            return new Result(StatusType.Error, REMOVED_ERROR_MSG);
        }
        return new Result(StatusType.Success, REMOVED_SUCCESS_MSG);
    }

    @Override
    public UserDTO find_by_id(Long id) {
        UserEntity userEntity = userRepository.find_by_id(id);
        if (userEntity == null) {
            return null;
        }
        return UserMapper.INSTANCE.map(userEntity);
    }
}
