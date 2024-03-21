package code.with.vanilson.department;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;
    private final DepartmentServiceExceptionHandlerProvider exceptionHandlerProvider;

    private static final String DEPARTMENT_NOT_FOUND_MESSAGE = "Department with ID %d not found";

    public DepartmentController(DepartmentServiceImpl departmentService,
                                DepartmentServiceExceptionHandlerProvider exceptionHandlerProvider) {
        this.departmentService = departmentService;
        this.exceptionHandlerProvider = exceptionHandlerProvider;
    }

    /**
     * Retrieves all departments.
     *
     * @return ResponseEntity with a list of DepartmentDto representing all departments.
     */
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departments = departmentService.findAllDepartments();
        return ResponseEntity.ok(departments);
    }

    /**
     * Retrieves a department by its ID.
     *
     * @param departmentId The ID of the department to retrieve.
     * @return ResponseEntity with DepartmentDto representing the found department or an error message if the department is not found.
     */
    @GetMapping("/{id}")

    public ResponseEntity<?> getDepartmentById(@PathVariable("id") long departmentId) {
        try {
            DepartmentDto department = departmentService.findDepartmentById(departmentId);
            return ResponseEntity.ok(department);
        } catch (DepartmentNotFoundException ex) {
            return exceptionHandlerProvider.handleDepartmentNotFound(ex);
        }
    }

    /**
     * Creates a new department.
     *
     * @param departmentDto The DTO representing the department to be created.
     * @return ResponseEntity with DepartmentDto representing the created department.
     */
    @PostMapping("/create-department")

    public ResponseEntity<DepartmentDto> createDepartment(
            @Valid @RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartment);
    }

    /**
     * Updates an existing department.
     *
     * @param departmentId  The ID of the department to update.
     * @param departmentDto The DTO representing the updated department information.
     * @return ResponseEntity with DepartmentDto representing the updated department, or a not found message if the department doesn't exist.
     */
    @PutMapping("/update-department/{id}")
    public ResponseEntity<?> updateDepartment(
            @PathVariable("id") long departmentId, @Valid @RequestBody DepartmentDto departmentDto) {
        if (departmentDto.getDepartmentId() != departmentId) {
            return exceptionHandlerProvider.handleDepartmentBadRequest(
                    new DepartmentBadRequestException("Invalid department ID provided"));
        }
        try {
            DepartmentDto updatedDepartment = departmentService.updateDepartment(departmentDto);
            return ResponseEntity.ok(updatedDepartment);
        } catch (DepartmentNotFoundException ex) {
            return exceptionHandlerProvider.handleDepartmentNotFound(ex);
        }
    }

    /**
     * Deletes a department by its ID.
     *
     * @param departmentId The ID of the department to delete.
     * @return ResponseEntity indicating successful deletion or a not found message if the department doesn't exist.
     */
    @DeleteMapping("/delete-department/{id}")
    public ResponseEntity<String> deleteDepartment(
            @PathVariable("id") long departmentId) {
        try {
            departmentService.deleteDepartment(departmentId);
            return ResponseEntity.noContent().build();
        } catch (DepartmentNotFoundException ex) {
            var errorMessage = String.format(DEPARTMENT_NOT_FOUND_MESSAGE, departmentId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorMessage);
        }
    }

}
