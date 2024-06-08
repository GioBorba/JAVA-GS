package fiap.com.br.exception;

public class InvalidCepException extends RuntimeException {
    public InvalidCepException(String message) {
        super(message);
    }
}
