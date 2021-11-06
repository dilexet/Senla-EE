package eu.senla.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class EventEntity {
    private Long Id;
    private String Name;
    private String Description;
    private Date Start_Date;

    private Set<RoleEntity> Roles;
    private Set<UserEntity> Users;
    private ChatEntity Chat;
}
