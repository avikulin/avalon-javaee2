package Common.Exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataValidationException extends Exception{
    private final List<String> ctx;

    private DataValidationException() {
        super();
        ctx = new ArrayList<>();
    }

    public DataValidationException(String message) {
        this();
        Objects.requireNonNull(message, "Error message list must be not-null");
        this.ctx.add(message);
    }

    public DataValidationException(String message, List<String> msgList) {
        this(message);
        Objects.requireNonNull(msgList, "Error messages list must be not-null");
        this.ctx.addAll(msgList);
    }

    public String getMessage(){
        return String.join(".\r\n", this.ctx);
    }

    public List<String> getMessages(){
        return new ArrayList<>(ctx);
    }

    public void addMessage(String msg){
        this.ctx.add(msg);
    }
}
