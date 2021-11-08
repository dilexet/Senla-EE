package eu.senla.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MessageDTO {

    private Long Id;
    private String text;
    @JsonFormat(pattern = "dd.mm.yyyy HH:mm:ss")
    @JsonProperty("send_date")
    private Date Send_Date;

    private UserDTO User;
    private ChatDTO Chat;
}
