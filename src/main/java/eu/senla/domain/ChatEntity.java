package eu.senla.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ChatEntity {
    private Long Id;
    private String Name;

    private Set<UserEntity> Users;
    private Set<MessageEntity> Messages;
    private EventEntity Event;
}
