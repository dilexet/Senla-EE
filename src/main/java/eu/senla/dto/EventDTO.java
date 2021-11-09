package eu.senla.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
public class EventDTO {

    private Long id;
    private String name;
    private String description;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    @JsonProperty("start_date")
    private Date startDate;

    private Set<RoleDTO> roles;
    private Set<UserDTO> users;
    private ChatDTO chat;

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
        EventDTO dto = (EventDTO) obj;
        return this.getId().equals(dto.getId());
    }
}
