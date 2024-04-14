package cl.thinka.clientmicroservice.v1.exception;

public class ThinkaException extends RuntimeException {
    public ThinkaException(String message) {
        super(message);
    }
}
