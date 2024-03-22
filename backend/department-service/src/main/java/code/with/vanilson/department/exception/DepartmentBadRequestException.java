package code.with.vanilson.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DepartmentBadRequestException extends RuntimeException {
    public DepartmentBadRequestException(String message) {
        super(message);
    }
}
