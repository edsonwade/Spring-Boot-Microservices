package code.with.vanilson.department;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
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
     * Finds a department by its ID.
     *
     * @param departmentId The ID of the department to find.
     * @return The DTO representing the found department.
     * @throws DepartmentNotFoundException if the department with the given ID is not found.
     */
    @Override
    public DepartmentDto findDepartmentById(long departmentId) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        return departmentOptional.map(department -> modelMapper.map(department, DepartmentDto.class))
                .orElseThrow(() -> new DepartmentNotFoundException(
                        MessageFormat.format("Department with ID {0} not found", departmentId)));
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
                MessageFormat.format("Department with ID {0} not found", departmentDto.getDepartmentId()));

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
        validateIfDepartmentIdExists(departmentId, "Department with ID " + departmentId + " not found");
        departmentRepository.deleteById(departmentId);
    }

    /**
     * Validates if a department with the specified ID exists in the repository.
     *
     * @param departmentId  The ID of the department to validate.
     * @param departmentId1 The ID of the department as a string representation (for error message).
     * @throws DepartmentNotFoundException if the department with the given ID is not found.
     */
    private void validateIfDepartmentIdExists(long departmentId, String departmentId1) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new DepartmentNotFoundException(departmentId1);
        }
    }

}
