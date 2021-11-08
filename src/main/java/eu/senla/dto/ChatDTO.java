package eu.senla.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ChatDTO {

    private Long Id;
    private String Name;

    private Set<UserDTO> Users;
    private Set<MessageDTO> Messages;
    private EventDTO Event;
}
