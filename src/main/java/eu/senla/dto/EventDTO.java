package eu.senla.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class EventDTO {
    @JsonProperty("id")
    private Long Id;

    @JsonProperty("name")
    private String Name;

    @JsonProperty("description")
    private String Description;

    @JsonProperty("start_date")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date Start_Date;

    @JsonProperty("roles")
    private Set<RoleDTO> Roles;

    @JsonProperty("users")
    private Set<UserDTO> Users;

    @JsonProperty("chat")
    private ChatDTO Chat;
}
