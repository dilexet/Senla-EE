package eu.senla.tools;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    private StatusType status;
    private String message;

    public Result(StatusType status, String message) {
        this.status = status;
        this.message = message;
    }
}
