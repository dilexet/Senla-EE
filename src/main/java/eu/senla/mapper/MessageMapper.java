package eu.senla.mapper;

import eu.senla.domain.MessageEntity;
import eu.senla.dto.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    MessageDTO map(MessageEntity message);
    MessageEntity map(MessageDTO message);
}
