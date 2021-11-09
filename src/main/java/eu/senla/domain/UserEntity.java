package eu.senla.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserEntity {
    private Long id;
    private String name;

    private Set<RoleEntity> roles;
    private Set<EventEntity> events;
    private Set<ChatEntity> chats;

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
        UserEntity entity = (UserEntity) obj;
        return this.getId().equals(entity.getId());
    }
}
