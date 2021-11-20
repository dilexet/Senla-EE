package eu.senla.mapper;

import eu.senla.domain.User;
import eu.senla.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO map(User user);
    User map(UserDTO user);
}
