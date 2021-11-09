package eu.senla.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MessageDTO {

    private Long id;
    private String text;
    @JsonFormat(pattern = "dd.mm.yyyy HH:mm:ss")
    @JsonProperty("send_date")
    private Date sendDate;

    private UserDTO user;
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
        MessageDTO dto = (MessageDTO) obj;
        return this.getId().equals(dto.getId());
    }
}
