package code.with.vanilson.department;

import code.with.vanilson.common.exception.CommonExceptionHandler;
import code.with.vanilson.common.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import static code.with.vanilson.common.exception.zone.CommonZoneRegionConstant.PORTUGAL_LISBON;
import static code.with.vanilson.http_codes.EndpointNumberConstant.BAD_REQUEST;
import static code.with.vanilson.http_codes.EndpointNumberConstant.NOT_FOUND;
import static code.with.vanilson.http_codes.HttpStatusCodes.BAD_REQUEST_STATUS;
import static code.with.vanilson.http_codes.HttpStatusCodes.NOT_FOUND_HTTP_STATUS;

@Component
@RestControllerAdvice
public class DepartmentExceptionHandler implements CommonExceptionHandler {

    @Override
    public ResponseEntity<ErrorResponse> handleNotFoundException(String ex) {
        ErrorResponse errorResponse =
                new ErrorResponse(ex, NOT_FOUND_HTTP_STATUS, PORTUGAL_LISBON, NOT_FOUND);
        return new ResponseEntity<>(errorResponse, NOT_FOUND_HTTP_STATUS);
    }

    @Override
    public ResponseEntity<ErrorResponse> handleBadRequestException(String ex) {
        var errorResponse =
                new ErrorResponse(ex, BAD_REQUEST_STATUS, PORTUGAL_LISBON, BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST_STATUS);
    }

    @Override
    public ResponseEntity<ErrorResponse> handleNumberException(String errorHandler) {
        var errorResponse =
                new ErrorResponse(errorHandler, BAD_REQUEST_STATUS, PORTUGAL_LISBON, BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST_STATUS);
    }
}
