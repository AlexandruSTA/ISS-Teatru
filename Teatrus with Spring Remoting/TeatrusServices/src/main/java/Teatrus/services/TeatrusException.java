package Teatrus.services;

public class TeatrusException extends Exception{
    public TeatrusException() {
    }

    public TeatrusException(String message) {
        super(message);
    }

    public TeatrusException(String message, Throwable cause) {
        super(message, cause);
    }
}
