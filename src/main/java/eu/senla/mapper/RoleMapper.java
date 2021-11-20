package eu.senla.mapper;

import eu.senla.domain.Role;
import eu.senla.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO map(Role role);
    Role map(RoleDTO role);
}
