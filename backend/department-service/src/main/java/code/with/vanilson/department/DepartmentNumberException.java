package code.with.vanilson.department;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DepartmentNumberException extends RuntimeException {
    public DepartmentNumberException(String message) {
        super(message);
    }

    public DepartmentNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}