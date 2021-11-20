package eu.senla.mapper;

import eu.senla.domain.Message;
import eu.senla.dto.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    MessageDTO map(Message message);
    Message map(MessageDTO message);
}
