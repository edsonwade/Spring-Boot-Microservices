package code.with.vanilson.department.exception.handler;

import code.with.vanilson.common.exception.CommonExceptionHandler;
import code.with.vanilson.common.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static code.with.vanilson.common.exception.zone.CommonZoneRegionConstant.PORTUGAL_LISBON;
import static code.with.vanilson.http_codes.EndpointNumberConstant.*;
import static code.with.vanilson.http_codes.HttpStatusCodes.BAD_REQUEST_STATUS;
import static code.with.vanilson.http_codes.HttpStatusCodes.NOT_FOUND_HTTP_STATUS;

@Component
@RestControllerAdvice
public class DepartmentExceptionHandler implements CommonExceptionHandler {

    @Override
    public ResponseEntity<ErrorResponse> handleNotFoundException(String ex) {
        ErrorResponse errorResponse =
                new ErrorResponse(ex, NOT_FOUND_HTTP_STATUS, PORTUGAL_LISBON, NOT_FOUND,LOCAL_DATE_TIME);
        return new ResponseEntity<>(errorResponse, NOT_FOUND_HTTP_STATUS);
    }

    @Override
    public ResponseEntity<ErrorResponse> handleBadRequestException(String ex) {
        var errorResponse =
                new ErrorResponse(ex, BAD_REQUEST_STATUS, PORTUGAL_LISBON, BAD_REQUEST,LOCAL_DATE_TIME);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST_STATUS);
    }

    @Override
    public ResponseEntity<ErrorResponse> handleConflictRequestException(String ex) {
        return null;
    }
}
