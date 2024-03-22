package code.with.vanilson.department.service;

import code.with.vanilson.department.dto.DepartmentDto;
import code.with.vanilson.department.exception.DepartmentBadRequestException;
import code.with.vanilson.department.exception.DepartmentNotFoundException;
import code.with.vanilson.department.model.Department;
import code.with.vanilson.department.repository.DepartmentRepository;
import code.with.vanilson.util.MessageProvider;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    public static final String DEPARTMENT_ERROR_NOT_FOUND = "department.error.not_found";

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Retrieves all departments and maps them to DTOs.
     *
     * @return List of DepartmentDto objects representing all departments.
     */
    @Override
    public List<DepartmentDto> findAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(department -> modelMapper.map(department, DepartmentDto.class))
                .toList();
    }

    /**
     * Saves a new department using the provided DTO.
     *
     * @param departmentDto The DTO representing the department to be saved.
     * @return The DTO representing the saved department.
     */
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        // Map DepartmentDto to Department entity
        Department department = modelMapper.map(departmentDto, Department.class);

        // Save the Department entity using the repository
        Department savedDepartment = departmentRepository.save(department);

        // Map the saved Department entity back to DepartmentDto
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    /**
     * Saves a list of departments.
     *
     * @param departmentDtos The list of department DTOs to be saved.
     * @return The list of department DTOs representing the saved departments.
     */

    @Override
    public List<DepartmentDto> saveDepartments(List<DepartmentDto> departmentDtos) {
        var departments = departmentDtos.stream()
                .map(departmentDto -> modelMapper.map(departmentDto, Department.class))
                .toList();

        // Save the list of Department entities using the repository
        var savedDepartments = departmentRepository.saveAll(departments);

        // Map the saved Department entities back to DepartmentDto
        return savedDepartments.stream()
                .map(savedDepartment -> modelMapper.map(savedDepartment, DepartmentDto.class))
                .toList();
    }

    /**
     * Finds a department by its ID.
     *
     * @param departmentId The ID of the department to find.
     * @return The DTO representing the found department.
     * @throws DepartmentNotFoundException   if the department with the given ID is not found.
     * @throws DepartmentBadRequestException with HttpStatus.BAD_REQUEST if departmentId is less than or equal to 0.
     */
    @Override
    public DepartmentDto findDepartmentById(long departmentId) {
        if (departmentId <= 0) {
            var errorMessage = MessageProvider.getMessage("department.error.bad_request", departmentId);
            throw new DepartmentBadRequestException(errorMessage);
        }
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        return departmentOptional.map(department -> modelMapper.map(department, DepartmentDto.class))
                .orElseThrow(() -> new DepartmentNotFoundException(
                        MessageFormat.format(MessageProvider.getMessage(DEPARTMENT_ERROR_NOT_FOUND), departmentId)));
    }

    /**
     * Updates an existing department using the provided DTO.
     *
     * @param departmentDto The DTO representing the department to be updated.
     * @return The DTO representing the updated department.
     * @throws DepartmentNotFoundException if the department with the given ID is not found.
     */
    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        // Check if the department with the given ID exists
        validateIfDepartmentIdExists(departmentDto.getDepartmentId(),
                MessageFormat.format(MessageProvider.getMessage(DEPARTMENT_ERROR_NOT_FOUND),
                        departmentDto.getDepartmentId()));

        // Map DepartmentDto to Department entity
        Department department = modelMapper.map(departmentDto, Department.class);

        // Save the updated Department entity using the repository
        Department updatedDepartment = departmentRepository.save(department);

        // Map the updated Department entity back to DepartmentDto
        return modelMapper.map(updatedDepartment, DepartmentDto.class);
    }

    /**
     * Deletes a department by its ID.
     *
     * @param departmentId The ID of the department to delete.
     * @throws DepartmentNotFoundException if the department with the given ID is not found.
     */
    @Override
    public void deleteDepartment(long departmentId) {
        validateIfDepartmentIdExists(departmentId, MessageFormat.format(MessageProvider.getMessage(
                DEPARTMENT_ERROR_NOT_FOUND), departmentId));
        departmentRepository.deleteById(departmentId);
    }

    /**
     * Validates if a department with the specified ID exists in the repository.
     *
     * @param departmentId  The ID of the department to validate.
     * @param departmentId1 The ID of the department as a string representation (for an error message).
     * @throws DepartmentNotFoundException if the department with the given ID is not found.
     */
    private void validateIfDepartmentIdExists(long departmentId, String departmentId1) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new DepartmentNotFoundException(departmentId1);
        }
    }

}
