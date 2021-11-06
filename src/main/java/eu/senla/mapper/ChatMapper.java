package eu.senla.mapper;

import eu.senla.domain.ChatEntity;
import eu.senla.dto.ChatDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChatMapper {
    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    ChatDTO map(ChatEntity chat);
    ChatEntity map(ChatDTO chat);
}
