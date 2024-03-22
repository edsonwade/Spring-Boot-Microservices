package code.with.vanilson.employee.exception;

import code.with.vanilson.common.exception.CommonExceptionHandler;
import org.springframework.stereotype.Component;

@Component
public class EmployeeServiceExceptionHandlerProvider {

    private final CommonExceptionHandler commonExceptionHandler;

    public EmployeeServiceExceptionHandlerProvider(CommonExceptionHandler commonExceptionHandler) {
        this.commonExceptionHandler = commonExceptionHandler;
    }
}