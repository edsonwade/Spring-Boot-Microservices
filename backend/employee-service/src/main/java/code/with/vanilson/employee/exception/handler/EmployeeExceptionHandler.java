package code.with.vanilson.employee.exception.handler;

import code.with.vanilson.common.exception.CommonExceptionHandler;
import code.with.vanilson.common.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static code.with.vanilson.common.exception.zone.CommonZoneRegionConstant.*;
import static code.with.vanilson.http_codes.EndpointNumberConstant.*;
import static code.with.vanilson.http_codes.HttpStatusCodes.*;

@Component
public class EmployeeExceptionHandler implements CommonExceptionHandler {

    @Override
    public ResponseEntity<ErrorResponse> handleNotFoundException(String ex) {
        ErrorResponse errorResponse =
                new ErrorResponse(ex, NOT_FOUND_HTTP_STATUS, PORTUGAL_LISBON, NOT_FOUND);
        return new ResponseEntity<>(errorResponse, NOT_FOUND_HTTP_STATUS);
    }

    @Override
    public ResponseEntity<ErrorResponse> handleBadRequestException(String ex) {
        ErrorResponse errorResponse =
                new ErrorResponse(ex, BAD_REQUEST_STATUS, UK_LONDON, BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST_STATUS);
    }

    @Override
    public ResponseEntity<ErrorResponse> handleConflictRequestException(String ex) {
        ErrorResponse errorResponse =
                new ErrorResponse(ex, CONFLICT_HTTP_STATUS, PORTUGAL_LISBON, CONFLICT);
        return new ResponseEntity<>(errorResponse, CONFLICT_HTTP_STATUS);
    }
}