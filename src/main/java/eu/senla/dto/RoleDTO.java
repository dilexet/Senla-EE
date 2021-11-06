package eu.senla.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.senla.domain.EventEntity;
import eu.senla.domain.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {
    @JsonProperty("id")
    private Long Id;

    @JsonProperty("name")
    private String Name;

    @JsonProperty("user")
    private UserEntity User;

    @JsonProperty("event")
    private EventEntity Event;
}
