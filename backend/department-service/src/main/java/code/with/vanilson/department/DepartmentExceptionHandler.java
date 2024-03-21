package code.with.vanilson.department;

import code.with.vanilson.common.exception.CommonExceptionHandler;
import code.with.vanilson.common.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import static code.with.vanilson.http_codes.EndpointNumberConstant.BAD_REQUEST;
import static code.with.vanilson.http_codes.EndpointNumberConstant.NOT_FOUND;

@Component
@RestControllerAdvice
public class DepartmentExceptionHandler implements CommonExceptionHandler {

    @Override
    public ResponseEntity<ErrorResponse> handleNotFoundException(String ex) {
        ErrorResponse errorResponse =
                new ErrorResponse(ex, HttpStatus.NOT_FOUND, "Europe/Lisbon", NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<ErrorResponse> handleBadRequestException(String ex) {
        ErrorResponse errorResponse =
                new ErrorResponse(ex, HttpStatus.BAD_REQUEST, "Europe/Lisbon", BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
