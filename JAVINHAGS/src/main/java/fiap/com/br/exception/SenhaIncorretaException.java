package fiap.com.br.exception;

public class SenhaIncorretaException extends RuntimeException {
    public SenhaIncorretaException(String message) {
        super(message);
    }
}
