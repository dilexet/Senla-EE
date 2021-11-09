package eu.senla.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ChatEntity {
    private Long id;
    private String name;

    private Set<UserEntity> users;
    private Set<MessageEntity> messages;
    private EventEntity event;

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
        ChatEntity entity = (ChatEntity) obj;
        return this.getId().equals(entity.getId());
    }
}
