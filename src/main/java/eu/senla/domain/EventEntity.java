package eu.senla.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class EventEntity {
    private Long id;
    private String name;
    private String description;
    private Date startDate;

    private Set<RoleEntity> roles;
    private Set<UserEntity> users;
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
        EventEntity entity = (EventEntity) obj;
        return this.getId().equals(entity.getId());
    }
}
