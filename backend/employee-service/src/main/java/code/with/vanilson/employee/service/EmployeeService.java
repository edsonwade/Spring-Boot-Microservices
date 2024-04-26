package code.with.vanilson.employee.service;

import code.with.vanilson.employee.dto.APIResponseDto;
import code.with.vanilson.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    /**
     * Retrieves all employees.
     *
     * @return A list of EmployeeDto representing all employees.
     */
    List<EmployeeDto> findAllEmployees();

    /**
     * Saves an employee.
     *
     * @param employeeDto The EmployeeDto to save.
     * @return The saved EmployeeDto.
     */
    EmployeeDto saveEmployeeDto(EmployeeDto employeeDto);

    /**
     * Finds an employee by their ID.
     *
     * @param employeeId The ID of the employee to find.
     * @return The found EmployeeDto.
     */
    EmployeeDto findEmployeeById(long employeeId);

    /**
     * Finds an employee by their email.
     *
     * @param employeeEmail The email of the employee to find.
     * @return The found EmployeeDto.
     */
    EmployeeDto findEmployeeByEmail(String employeeEmail);

    /**
     * Updates an employee.
     *
     * @param employeeDto The EmployeeDto to update.
     * @return The updated EmployeeDto.
     */
    EmployeeDto updateEmployeeDto(EmployeeDto employeeDto);

    /**
     * Retrieves an employee along with their department information by their ID.
     *
     * @param employeeId The ID of the employee to retrieve.
     * @return An APIResponseDto containing the EmployeeDto and associated DepartmentDto.
     */
    APIResponseDto getEmployeeWithDepartmentById(Long employeeId);

    /**
     * Deletes an employee by their ID.
     *
     * @param employeeId The ID of the employee to delete.
     */
    void deleteEmployee(long employeeId);
}
