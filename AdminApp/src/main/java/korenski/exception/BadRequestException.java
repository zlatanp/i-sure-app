package korenski.exception;

/**
 * Created by Jasmina on 16/11/2017.
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException() {}

    public BadRequestException(String message) {
        super(message);
    }
}
