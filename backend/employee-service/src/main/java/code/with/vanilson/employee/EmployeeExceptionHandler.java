package code.with.vanilson.employee;

import code.with.vanilson.common.exception.CommonExceptionHandler;
import code.with.vanilson.common.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeExceptionHandler implements CommonExceptionHandler {

    @Override
    public ResponseEntity<ErrorResponse> handleNotFoundException(String ex) {
        ErrorResponse errorResponse =
                new ErrorResponse(ex, HttpStatus.NOT_FOUND, "Europe/Lisbon", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<ErrorResponse> handleBadRequestException(String ex) {
        ErrorResponse errorResponse =
                new ErrorResponse(ex, HttpStatus.BAD_REQUEST, "Europe/Lisbon", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}