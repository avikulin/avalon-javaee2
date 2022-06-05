package Views;

import Common.Exceptions.DataValidationException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface WebForm {
    void fillFrom(HttpServletRequest request) throws DataValidationException;
    void transferTo(HttpServletRequest request);
    boolean isValid();
    List<String> getErrorsList();
}
