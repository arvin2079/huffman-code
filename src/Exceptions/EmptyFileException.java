package Exceptions;

public class EmptyFileException extends RuntimeException {

    public EmptyFileException() {
        super();
    }

    public EmptyFileException(String message) {
        super(message);
    }
}
