package code.with.vanilson.employee.controller;

import code.with.vanilson.employee.dto.APIResponseDto;
import code.with.vanilson.employee.dto.EmployeeDto;
import code.with.vanilson.employee.exception.EmployeeBadRequestException;
import code.with.vanilson.employee.exception.EmployeeNotFoundException;
import code.with.vanilson.employee.exception.EmployeeServiceExceptionHandlerProvider;
import code.with.vanilson.employee.exception.EmployeeWithEmailAlreadyExistsException;
import code.with.vanilson.employee.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static code.with.vanilson.http_codes.EndpointNumberConstant.NOT_FOUND;
import static code.with.vanilson.util.MessageProvider.getMessage;

@RestController
@RequestMapping(path = "/api/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    private final EmployeeServiceExceptionHandlerProvider handlerProvider;

    public EmployeeController(EmployeeServiceImpl employeeService,
                              EmployeeServiceExceptionHandlerProvider handlerProvider) {
        this.employeeService = employeeService;
        this.handlerProvider = handlerProvider;
    }

    private static final String EMPLOYEE_NOT_FOUND_MESSAGE = "employee.error.not_found";

    /**
     * Retrieves all employees.
     *
     * @return ResponseEntity with a list of EmployeeDto representing all employees.
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.findAllEmployees();
        return ResponseEntity.ok(employees);
    }

    /**
     * Retrieves an employee by its ID.
     *
     * @param employeeId The ID of the employee to retrieve.
     * @return ResponseEntity with EmployeeDto representing the found employee or an error message if the employee is not found.
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") long employeeId) {
        try {
            EmployeeDto employee = employeeService.findEmployeeById(employeeId);
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException ex) {
            return handlerProvider.handleEmployeeNotFound(ex);
        } catch (EmployeeBadRequestException ex) {
            return handlerProvider.handleEmployeeBadRequest(ex);
        }
    }

    /**
     * Retrieves an employee by their email address.
     *
     * @param email The email address of the employee to retrieve.
     * @return ResponseEntity with EmployeeDto representing the found employee or an error message if the employee is not found.
     */
    @GetMapping(value = "/by-email")
    public ResponseEntity<?> getEmployeeByEmail(@RequestParam("email") String email) {
        try {
            EmployeeDto employee = employeeService.findEmployeeByEmail(email);
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException ex) {
            var errorMessage = getMessage("employee.error.email.not_found", email);
            return ResponseEntity.status(NOT_FOUND)
                    .body(errorMessage);
        }

    }

    /**
     * Builds the Get Employee REST API endpoint.
     * Retrieves an employee and their associated department information by their ID.
     *
     * @param employeeId The ID of the employee to retrieve.
     * @return A ResponseEntity containing an APIResponseDto representing the employee and their department.
     */
    @GetMapping("/departments/{id}")
    public ResponseEntity<APIResponseDto> getEmployee(@PathVariable("id") Long employeeId) {
        APIResponseDto apiResponseDto = employeeService.getEmployeeWithDepartmentById(employeeId);
        return ResponseEntity.ok(apiResponseDto);
    }

    /**
     * Creates a new employee.
     *
     * @param employeeDto The DTO representing the employee to be created.
     * @return ResponseEntity with EmployeeDto representing the created employee.
     */
    @PostMapping("/create-employee")
    public ResponseEntity<?> createEmployee(
            @Valid @RequestBody EmployeeDto employeeDto) {

        try {

            EmployeeDto saveEmployee = employeeService.saveEmployeeDto(employeeDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveEmployee);
        } catch (EmployeeWithEmailAlreadyExistsException ex) {
            return handlerProvider.handleEmployeeWithEmailAlreadyExistException(ex);
        }

    }

    /**
     * Updates an existing employee.
     *
     * @param employeeId  The ID of the employee to update.
     * @param employeeDto The DTO representing the updated employee information.
     * @return ResponseEntity with EmployeeDto representing the updated employee, or a not found message if the employee doesn't exist.
     */
    @PutMapping("/update-employee/{id}")

    public ResponseEntity<?> updateEmployee(@PathVariable("id") long employeeId,
                                            @Valid @RequestBody EmployeeDto employeeDto) {
        if (employeeDto.getEmployeeId() != employeeId) {
            return ResponseEntity.badRequest().build();
        }
        try {
            EmployeeDto updateEmployeeDto = employeeService.updateEmployeeDto(employeeDto);
            return ResponseEntity.ok(updateEmployeeDto);
        } catch (EmployeeNotFoundException ex) {
            return handlerProvider.handleEmployeeNotFound(ex);
        } catch (EmployeeBadRequestException ex) {
            return handlerProvider.handleEmployeeBadRequest(ex);
        } catch (EmployeeWithEmailAlreadyExistsException ex) {
            return handlerProvider.handleEmployeeWithEmailAlreadyExistException(ex);
        }
    }

    /**
     * Deletes an employee by its ID.
     *
     * @param employeeId The ID of the employee to delete.
     * @return ResponseEntity indicating successful deletion or a not found message if the employee doesn't exist.
     */
    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable("id") long employeeId) {
        try {
            employeeService.deleteEmployee(employeeId);
            return ResponseEntity.noContent().build();
        } catch (EmployeeNotFoundException ex) {
            var errorMessage = getMessage(EMPLOYEE_NOT_FOUND_MESSAGE, employeeId);
            return ResponseEntity.status(NOT_FOUND)
                    .body(errorMessage);
        }
    }

}
