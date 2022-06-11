package Common.Exceptions;

public class DataValidationException extends Exception{
    public DataValidationException() {
        super();
    }

    public DataValidationException(String message) {
        super(message);
    }
}
