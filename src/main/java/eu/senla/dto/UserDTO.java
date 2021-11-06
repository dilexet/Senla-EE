package eu.senla.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO {
    @JsonProperty("id")
    private Long Id;

    @JsonProperty("name")
    private String Name;

    @JsonProperty("roles")
    private Set<RoleDTO> Roles;

    @JsonProperty("events")
    private Set<EventDTO> Events;

    @JsonProperty("chats")
    private Set<ChatDTO> Chats;
}
