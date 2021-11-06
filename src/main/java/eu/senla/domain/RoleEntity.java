package eu.senla.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleEntity {
    private Long Id;
    private String Name;

    private UserEntity User;
    private EventEntity Event;
}
