package eu.senla.tools;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private int code;
    private Object object;

    public Response(int code, Object object) {
        this.code = code;
        this.object = object;
    }
}
