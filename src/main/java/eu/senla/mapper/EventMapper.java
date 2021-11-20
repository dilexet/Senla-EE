package eu.senla.mapper;

import eu.senla.domain.Event;
import eu.senla.dto.EventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    EventDTO map(Event event);
    Event map(EventDTO event);
}
