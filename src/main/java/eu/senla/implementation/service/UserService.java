package eu.senla.implementation.service;

import eu.senla.abstraction.dao.UserRepositoryInterface;
import eu.senla.abstraction.service.UserServiceInterface;
import eu.senla.domain.UserEntity;
import eu.senla.dto.UserDTO;
import eu.senla.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepositoryInterface userRepository;

    public UserService(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String create(UserDTO user) {
        UserEntity userEntity = UserMapper.INSTANCE.map(user);
        boolean result = userRepository.add(userEntity);
        if (!result) {
            return "Error";
        }
        return "Success";
    }

    @Override
    public String update(UserDTO user) {
        UserEntity userEntity = UserMapper.INSTANCE.map(user);
        boolean result = userRepository.update(userEntity);
        if (!result) {
            return "Error";
        }
        return "Success";
    }

    @Override
    public String remove(Long id) {
        boolean result = userRepository.remove(id);
        if (!result) {
            return "Error";
        }
        return "Success";
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
