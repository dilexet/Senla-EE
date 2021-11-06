package eu.senla.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserEntity {
    private Long Id;
    private String Name;

    private Set<RoleEntity> Roles;
    private Set<EventEntity> Events;
    private Set<ChatEntity> Chats;

}
