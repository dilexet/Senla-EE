package eu.senla.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
public class UserDTO {

    private Long Id;
    private String Name;

    private Set<RoleDTO> Roles;
    private Set<EventDTO> Events;
    private Set<ChatDTO> Chats;
}
