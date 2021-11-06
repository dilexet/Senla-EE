package eu.senla.mapper;

import eu.senla.domain.RoleEntity;
import eu.senla.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO map(RoleEntity role);
    RoleEntity map(RoleDTO role);
}
