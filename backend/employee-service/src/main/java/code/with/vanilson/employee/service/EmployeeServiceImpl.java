package code.with.vanilson.employee.service;

import code.with.vanilson.employee.dto.APIResponseDto;
import code.with.vanilson.employee.dto.DepartmentDto;
import code.with.vanilson.employee.dto.EmployeeDepartmentWrapper;
import code.with.vanilson.employee.dto.EmployeeDto;
import code.with.vanilson.employee.exception.EmployeeBadRequestException;
import code.with.vanilson.employee.exception.EmployeeNotFoundException;
import code.with.vanilson.employee.exception.EmployeeWithEmailAlreadyExistsException;
import code.with.vanilson.employee.model.Employee;
import code.with.vanilson.employee.repository.EmployeeRepository;
import code.with.vanilson.util.MessageProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final RestTemplate restTemplate;

    private final ModelMapper modelMapper;

    @Value("${department.service.url}")
    private String departmentServiceUrl;

    private static final String EMPLOYEE_NOT_FOUND_MESSAGE = "employee.error.not_found";

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, RestTemplate restTemplate,
                               ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.restTemplate = restTemplate;
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
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return employee.map(department -> modelMapper.map(department, EmployeeDto.class))
                .orElseThrow(() -> new EmployeeNotFoundException(
                        MessageFormat.format(MessageProvider.getMessage(EMPLOYEE_NOT_FOUND_MESSAGE), employeeId)));

    }

    public APIResponseDto getEmployeeById(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        String departmentCode = employee.getDepartmentCode();

        // Fetch department information by department code to get the department ID
        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
                "http://localhost:8082/api/departments?code=" + departmentCode, DepartmentDto.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            // Handle case where department is not found
            throw new EmployeeNotFoundException("Department not found");
        }

        DepartmentDto department = responseEntity.getBody();

        EmployeeDto employeeDto = new EmployeeDto(
                employee.getEmployeeId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(department);

        return apiResponseDto;
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

//    public List<EmployeeDto> findByDepartmentId(long departmentId) {
//        // Fetching department details by its ID
//        ResponseEntity<DepartmentDto> departmentResponse =
//                restTemplate.getForEntity(departmentServiceUrl + departmentId, DepartmentDto.class);
//
//        if (departmentResponse.getStatusCode() == HttpStatus.OK) {
//            var departmentDTO = departmentResponse.getBody();
//
//            // Ensure departmentDTO is not null
//            DepartmentDto departmentDtoNotNull =
//                    Optional.ofNullable(departmentDTO)
//                            .orElseThrow(() -> new EmployeeNotFoundException("Department details are null"));
//
//            // Fetching employee by department ID (assuming there's only one employee per department)
//            Optional<Employee> employeeOptional =
//                    employeeRepository.findById(departmentDtoNotNull.getDepartmentId());
//
//            Employee employee = employeeOptional.orElseThrow(
//                    () -> new EmployeeNotFoundException("No employee found for the given department"));
//
//            // Map the single employee to DTO
//            EmployeeDto employeeDto = mapToEmployeeDTO(employee);
//
//            // Return a list containing the single employee DTO
//            return Collections.singletonList(employeeDto);
//        } else {
//            throw new EmployeeNotFoundException("Department not found");
//        }
//    }

    public EmployeeDepartmentWrapper findByDepartmentId(long departmentId) {
        // Fetching department details by its ID
        ResponseEntity<DepartmentDto> departmentResponse =
                restTemplate.getForEntity(departmentServiceUrl + departmentId, DepartmentDto.class);

        if (departmentResponse.getStatusCode() == HttpStatus.OK) {
            var departmentDTO = departmentResponse.getBody();

            // Ensure departmentDTO is not null
            DepartmentDto departmentDtoNotNull =
                    Optional.ofNullable(departmentDTO)
                            .orElseThrow(() -> new EmployeeNotFoundException("Department details are null"));

            // Fetching employees by department ID (assuming there's only one employee per department)
            Optional<Employee> employeeOptional =
                    employeeRepository.findById(departmentDtoNotNull.getDepartmentId());

            Employee employee = employeeOptional.orElseThrow(
                    () -> new EmployeeNotFoundException("No employee found for the given department"));

            // Map the employee to DTO
            EmployeeDto employeeDto = mapToEmployeeDTO(employee);

            return new EmployeeDepartmentWrapper(departmentDtoNotNull, employeeDto);
        } else {
            throw new EmployeeNotFoundException("Department not found");
        }
    }

    private EmployeeDto mapToEmployeeDTO(Employee employee) {
        var employeeDTO = new EmployeeDto();
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setDepartmentCode(employee.getDepartmentCode());
        return employeeDTO;
    }
}
