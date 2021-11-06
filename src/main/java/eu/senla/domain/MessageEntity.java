package eu.senla.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageEntity {
    private Long Id;
    private String text;
    private Date Send_Date;

    private UserEntity User;
    private ChatEntity Chat;
}
