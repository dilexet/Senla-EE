package eu.senla.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ChatDTO {

    private Long id;
    private String name;

    private Set<UserDTO> users;
    private Set<MessageDTO> messages;
    private EventDTO event;

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
        ChatDTO dto = (ChatDTO) obj;
        return this.getId().equals(dto.getId());
    }
}
