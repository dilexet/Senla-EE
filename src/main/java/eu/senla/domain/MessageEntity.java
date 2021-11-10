package eu.senla.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageEntity {
    private Long id;
    private String text;
    private Date sendDate;

    private UserEntity user;
    private ChatEntity chat;

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
        MessageEntity entity = (MessageEntity) obj;
        return this.getId().equals(entity.getId());
    }
}
