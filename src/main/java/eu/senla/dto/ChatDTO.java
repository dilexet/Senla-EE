package eu.senla.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.senla.domain.EventEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ChatDTO {
    @JsonProperty("id")
    private Long Id;

    @JsonProperty("name")
    private String Name;

    @JsonProperty("users")
    private Set<UserDTO> Users;

    @JsonProperty("messages")
    private Set<MessageDTO> Messages;

    @JsonProperty("event")
    private EventEntity Event;
}
