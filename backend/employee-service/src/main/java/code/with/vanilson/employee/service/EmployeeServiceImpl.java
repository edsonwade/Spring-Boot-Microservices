package code.with.vanilson.employee.service;

import code.with.vanilson.employee.dto.EmployeeDto;
import code.with.vanilson.employee.exception.EmployeeBadRequestException;
import code.with.vanilson.employee.exception.EmployeeNotFoundException;
import code.with.vanilson.employee.exception.EmployeeWithEmailAlreadyExistsException;
import code.with.vanilson.employee.model.Employee;
import code.with.vanilson.employee.repository.EmployeeRepository;
import code.with.vanilson.util.MessageProvider;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    private static final String EMPLOYEE_NOT_FOUND_MESSAGE = "employee.error.not_found";

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Retrieves all employees and maps them to DTOs.
     *
     * @return List of EmployeeDto objects representing all employees.
     */
    @Override
    public List<EmployeeDto> findAllEmployees() {
        List<Employee> departments = employeeRepository.findAll();
        return departments.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .toList();
    }

    /**
     * Finds an employee by its ID.
     *
     * @param employeeId The ID of the employee to find.
     * @return The DTO representing the found employee.
     * @throws EmployeeNotFoundException if the employee with the given ID is not found.
     */
    @Override
    public EmployeeDto findEmployeeById(long employeeId) {
        if (employeeId <= 0) {
            var errorMessage = MessageProvider.getMessage("employee.error.bad_request", employeeId);
            throw new EmployeeBadRequestException(errorMessage);
        }
        Optional<Employee> departmentOptional = employeeRepository.findById(employeeId);
        return departmentOptional.map(department -> modelMapper.map(department, EmployeeDto.class))
                .orElseThrow(() -> new EmployeeNotFoundException(
                        MessageFormat.format(MessageProvider.getMessage(EMPLOYEE_NOT_FOUND_MESSAGE), employeeId)));

    }

    /**
     * Finds an employee by its Email.
     *
     * @param employeeEmail The email of the employee to find.
     * @return The DTO representing the found employee.
     * @throws EmployeeNotFoundException if the employee with the given EMAIL is not found.
     */
    @Override
    public EmployeeDto findEmployeeByEmail(String employeeEmail) {
        Optional<Employee> departmentOptional =
                Optional.ofNullable(employeeRepository.findEmployeeByEmail(employeeEmail));
        return departmentOptional
                .map(department -> modelMapper.map(department, EmployeeDto.class))
                .orElseThrow(() -> new EmployeeNotFoundException(
                        MessageFormat.format(MessageProvider.getMessage("employee.error.email.not_found"),
                                employeeEmail)));

    }

    /**
     * Saves an employee based on the provided EmployeeDto.
     *
     * @param employeeDto The EmployeeDto containing employee information to be saved.
     * @return The saved EmployeeDto after persisting the data.
     * @throws EmployeeWithEmailAlreadyExistsException If an employee with the specified email already exists.
     */
    @Override
    public EmployeeDto saveEmployeeDto(EmployeeDto employeeDto) {
        // Map DepartmentDto to Department entity
        Employee employee = modelMapper.map(employeeDto, Employee.class);

        // check if the email already exists
        validateEmailAndThrowException(employee.getEmail());

        // Save the Employee entity using the repository
        Employee savedEmployee = employeeRepository.save(employee);

        // Map the saved Employee entity back to EmployeeDto
        return modelMapper.map(savedEmployee, EmployeeDto.class);

    }

    /**
     * Updates an employee based on the provided EmployeeDto.
     *
     * @param employeeDto The EmployeeDto containing updated employee information.
     * @return The updated EmployeeDto after saving the changes.
     * @throws EmployeeNotFoundException               If the employee with the specified ID does not exist.
     * @throws EmployeeWithEmailAlreadyExistsException If an employee with the specified email already exists.
     */

    @Override
    public EmployeeDto updateEmployeeDto(EmployeeDto employeeDto) {
        // Check if the employee with the given ID exists
        validateIfEmployeeIdExists(employeeDto.getEmployeeId(),
                MessageFormat.format("EmployeeDto with ID {0} not found", employeeDto.getEmployeeId()));
        // Check if the employee with the given email exists
        validateEmailAndThrowException(employeeDto.getEmail());

        // Map EmployeeDto to Employee entity
        Employee employee = modelMapper.map(employeeDto, Employee.class);

        // Save the updated Employee entity using the repository
        Employee updatedEmployee = employeeRepository.save(employee);

        // Map the updated Employee entity back to EmployeeDto
        return modelMapper.map(updatedEmployee, EmployeeDto.class);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new EmployeeNotFoundException(
                    MessageFormat.format(MessageProvider.getMessage(EMPLOYEE_NOT_FOUND_MESSAGE), employeeId));
        }
        employeeRepository.deleteById(employeeId);
    }

    /**
     * Validates whether an employee with the given ID exists.
     *
     * @param employeeId The ID of the employee to check.
     * @param empId      The employee identifier (used for an exception message).
     * @throws EmployeeNotFoundException If the employee with the specified ID does not exist.
     */
    private void validateIfEmployeeIdExists(long employeeId, String empId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new EmployeeNotFoundException(empId);
        }
    }

    /**
     * Validates whether an employee with the given email already exists.
     *
     * @param email The email address to check.
     * @throws EmployeeWithEmailAlreadyExistsException If an employee with the specified email already exists.
     */
    public void validateEmailAndThrowException(String email) {
        if (employeeRepository.existsByEmail(email)) {
            throw new EmployeeWithEmailAlreadyExistsException(
                    MessageFormat.format(MessageProvider.getMessage("employee.error.email.conflict_request"), email));
        }
    }
}
