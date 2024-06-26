package code.with.vanilson.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmployeeWithEmailAlreadyExistsException extends RuntimeException {
    public EmployeeWithEmailAlreadyExistsException(String message) {
        super(message);
    }
}
