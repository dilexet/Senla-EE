package eu.senla.mapper;

import eu.senla.domain.EventEntity;
import eu.senla.dto.EventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    EventDTO map(EventEntity event);
    EventEntity map(EventDTO event);
}
