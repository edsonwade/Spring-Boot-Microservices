package code.with.vanilson.employee.exception;

import code.with.vanilson.common.exception.CommonExceptionHandler;
import code.with.vanilson.common.exception.ErrorMessageGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
public class EmployeeServiceExceptionHandlerProvider {

    private final CommonExceptionHandler commonExceptionHandler;

    public EmployeeServiceExceptionHandlerProvider(CommonExceptionHandler commonExceptionHandler) {
        this.commonExceptionHandler = commonExceptionHandler;
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleEmployeeNotFound(EmployeeNotFoundException ex) {
        String errorMessage = ErrorMessageGenerator.generateEmployeeNotFoundMessage(ex.getMessage());
        return commonExceptionHandler.handleNotFoundException(errorMessage);
    }

    @ExceptionHandler(EmployeeBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleEmployeeBadRequest(EmployeeBadRequestException ex) {
        String errorMessage = ErrorMessageGenerator.generateEmployeeBadRequestMessage(ex.getMessage());
        return commonExceptionHandler.handleBadRequestException(errorMessage);
    }

    @ExceptionHandler(EmployeeWithEmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<?> handleEmployeeWithEmailAlreadyExistException(EmployeeWithEmailAlreadyExistsException ex) {
        String errorMessage = ErrorMessageGenerator.generateEmployeeEmailConflitRequestMessage(ex.getMessage());
        return commonExceptionHandler.handleConflictRequestException(errorMessage);
    }
}