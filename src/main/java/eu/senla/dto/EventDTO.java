package eu.senla.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
public class EventDTO {

    private Long Id;
    private String Name;
    private String Description;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    @JsonProperty("start_date")
    private Date Start_Date;

    private Set<RoleDTO> Roles;
    private Set<UserDTO> Users;
    private ChatDTO Chat;
}
