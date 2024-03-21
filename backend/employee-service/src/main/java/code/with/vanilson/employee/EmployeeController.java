package code.with.vanilson.employee;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    private static final String EMPLOYEE_NOT_FOUND_MESSAGE = "Employee with ID %d not found";
    private static final String EMPLOYEE_EMAIL_NOT_FOUND_MESSAGE = "Employee with ID %s not found";

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Retrieves all employees.
     *
     * @return ResponseEntity with a list of EmployeeDto representing all employees.
     */
    @GetMapping
    @ApiOperation(value = "Get all employees", notes = "Retrieves all employees.")
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
    @GetMapping("/{id}")
    @ApiOperation(value = "Get employee by ID", notes = "Retrieves a employee by its ID.")
    public ResponseEntity<?> getEmployeeById(
            @ApiParam(value = "ID of the employee to retrieve", example = "1")
            @PathVariable("id") long employeeId) {
        try {
            EmployeeDto employee = employeeService.findEmployeeById(employeeId);
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException ex) {
            var errorMessage = String.format(EMPLOYEE_NOT_FOUND_MESSAGE, employeeId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorMessage);
        }
    }

    /**
     * Retrieves an employee by their email address.
     *
     * @param email The email address of the employee to retrieve.
     * @return ResponseEntity with EmployeeDto representing the found employee or an error message if the employee is not found.
     */
    @GetMapping("/by-email")
    @ApiOperation(value = "Get employee by email", notes = "Retrieves an employee by their email address.")
    public ResponseEntity<?> getEmployeeByEmail(
            @ApiParam(value = "Email address of the employee to retrieve", example = "john.doe@example.com")
            @RequestParam("email") String email) {
        try {
            EmployeeDto employee = employeeService.findEmployeeByEmail(email);
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException ex) {
            var errorMessage = String.format(EMPLOYEE_EMAIL_NOT_FOUND_MESSAGE, email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorMessage);
        }
    }


    /**
     * Creates a new employee.
     *
     * @param employeeDto The DTO representing the employee to be created.
     * @return ResponseEntity with EmployeeDto representing the created employee.
     */
    @PostMapping("/create-employee")
    @ApiOperation(value = "Create new employee", notes = "Creates a new employee.")
    public ResponseEntity<EmployeeDto> createEmployee(
            @ApiParam(value = "Employee information", required = true)
            @Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto saveEmployee = employeeService.saveEmployeeDto(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveEmployee);
    }

    /**
     * Updates an existing employee.
     *
     * @param employeeId  The ID of the employee to update.
     * @param employeeDto The DTO representing the updated employee information.
     * @return ResponseEntity with EmployeeDto representing the updated employee, or a not found message if the employee doesn't exist.
     */
    @PutMapping("/update-employee/{id}")
    @ApiOperation(value = "Update employee", notes = "Updates an existing employee.")
    public ResponseEntity<?> updateEmployee(
            @ApiParam(value = "ID of the employee to update", example = "1")
            @PathVariable("id") long employeeId,
            @ApiParam(value = "Updated employee information", required = true)
            @Valid @RequestBody EmployeeDto employeeDto) {
        if (employeeDto.getEmployeeId() != employeeId) {
            return ResponseEntity.badRequest().build();
        }
        try {
            EmployeeDto updateEmployeeDto = employeeService.updateEmployeeDto(employeeDto);
            return ResponseEntity.ok(updateEmployeeDto);
        } catch (EmployeeNotFoundException ex) {
            var errorMessage = String.format(EMPLOYEE_NOT_FOUND_MESSAGE, employeeId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorMessage);
        }
    }

    /**
     * Deletes an employee by its ID.
     *
     * @param employeeId The ID of the employee to delete.
     * @return ResponseEntity indicating successful deletion or a not found message if the employee doesn't exist.
     */
    @DeleteMapping("/delete-employee/{id}")
    @ApiOperation(value = "Delete employee", notes = "Deletes a employee by its ID.")
    public ResponseEntity<String> deleteEmployee(
            @ApiParam(value = "ID of the employee to delete", example = "1")
            @PathVariable("id") long employeeId) {
        try {
            employeeService.deleteEmployee(employeeId);
            return ResponseEntity.noContent().build();
        } catch (EmployeeNotFoundException ex) {
            var errorMessage = String.format(EMPLOYEE_NOT_FOUND_MESSAGE, employeeId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorMessage);
        }
    }

}
