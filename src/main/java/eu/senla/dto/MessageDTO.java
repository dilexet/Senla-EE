package eu.senla.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.senla.domain.ChatEntity;
import eu.senla.domain.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageDTO {
    @JsonProperty("id")
    private Long Id;

    @JsonProperty("text")
    private String text;

    @JsonProperty("send_date")
    @JsonFormat(pattern = "dd.mm.yyyy HH:mm")
    private Date Send_Date;

    @JsonProperty("user")
    private UserEntity User;

    @JsonProperty("chat")
    private ChatEntity Chat;
}
