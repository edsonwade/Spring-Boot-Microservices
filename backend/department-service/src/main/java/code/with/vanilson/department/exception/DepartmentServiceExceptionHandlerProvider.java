package code.with.vanilson.department.exception;

import code.with.vanilson.common.exception.CommonExceptionHandler;
import code.with.vanilson.common.exception.ErrorMessageGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
public class DepartmentServiceExceptionHandlerProvider {

    private final CommonExceptionHandler commonExceptionHandler;

    public DepartmentServiceExceptionHandlerProvider(CommonExceptionHandler commonExceptionHandler) {
        this.commonExceptionHandler = commonExceptionHandler;
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleDepartmentNotFound(DepartmentNotFoundException ex) {
        String errorMessage = ErrorMessageGenerator.generateDepartmentNotFoundMessage(ex.getMessage());
        return commonExceptionHandler.handleNotFoundException(errorMessage);
    }

    @ExceptionHandler(DepartmentBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleDepartmentBadRequest(DepartmentBadRequestException ex) {
        String errorMessage = ErrorMessageGenerator.generateDepartmentBadRequestMessage(ex.getMessage());
        return commonExceptionHandler.handleBadRequestException(errorMessage);
    }

    @ExceptionHandler(DepartmentNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleDepartmentNumberBadRequest(DepartmentNumberException ex) {
        String errorMessage = ErrorMessageGenerator.generateDepartmentBadRequestMessage(ex.getMessage());
        return commonExceptionHandler.handleNumberException(errorMessage);
    }
}