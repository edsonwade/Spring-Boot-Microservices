package code.with.vanilson.common.exception;

import org.springframework.http.ResponseEntity;


public interface CommonExceptionHandler {
    ResponseEntity<ErrorResponse> handleNotFoundException(String ex);
    ResponseEntity<ErrorResponse> handleBadRequestException(String ex);
}
