package cl.thinka.clientmicroservice.v1.util;


import lombok.Data;

@Data
public class ThinkaResponse<T> {
    private int code;
    private String message;
    private T data;

    public ThinkaResponse() {
    }

    public ThinkaResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ThinkaResponse(int code, String message) {
        this(code, message, (T) message);
    }
}
