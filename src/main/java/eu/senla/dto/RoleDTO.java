package eu.senla.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDTO {

    private Long Id;
    private String Name;

    private UserDTO User;
    private EventDTO Event;
}
