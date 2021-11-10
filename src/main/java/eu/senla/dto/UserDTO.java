package eu.senla.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;

    private Set<RoleDTO> roles;
    private Set<EventDTO> events;
    private Set<ChatDTO> chats;

    @Override
    public int hashCode() {
        final long prime = 31L;
        long result = 1L;
        result = prime * result + this.getId();
        return (int) result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (this == obj)
            return true;
        UserDTO dto = (UserDTO) obj;
        return this.getId().equals(dto.getId());
    }
}
