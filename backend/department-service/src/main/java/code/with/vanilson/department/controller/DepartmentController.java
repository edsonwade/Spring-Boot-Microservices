package code.with.vanilson.department.controller;

import code.with.vanilson.department.dto.DepartmentDto;
import code.with.vanilson.department.exception.DepartmentBadRequestException;
import code.with.vanilson.department.exception.DepartmentNotFoundException;
import code.with.vanilson.department.exception.DepartmentServiceExceptionHandlerProvider;
import code.with.vanilson.department.service.DepartmentServiceImpl;
import code.with.vanilson.util.MessageProvider;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

import static code.with.vanilson.department.service.DepartmentServiceImpl.DEPARTMENT_ERROR_NOT_FOUND;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;
    private final DepartmentServiceExceptionHandlerProvider exceptionHandlerProvider;

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

    @GetMapping("/{id}")

    public ResponseEntity<?> getDepartmentById(@PathVariable("id") long departmentId) {
        try {
            DepartmentDto department = departmentService.findDepartmentById(departmentId);
            return ResponseEntity.ok(department);
        } catch (DepartmentNotFoundException ex) {
            return exceptionHandlerProvider.handleDepartmentNotFound(ex);
        } catch (DepartmentBadRequestException ex) {
            return exceptionHandlerProvider.handleDepartmentBadRequest(ex);
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
     * Saves a list of departments.
     *
     * @param departmentDtos The list of department DTOs to be saved.
     * @return ResponseEntity with a list of department DTOs representing the saved departments,
     * along with a status code of 201 (Created).
     */

    @PostMapping("/save-departments")
    public ResponseEntity<List<DepartmentDto>> saveDepartments(@RequestBody List<DepartmentDto> departmentDtos) {
        List<DepartmentDto> savedDepartments = departmentService.saveDepartments(departmentDtos);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedDepartments);
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
            var errorMessage =
                    MessageFormat.format(MessageProvider.getMessage(DEPARTMENT_ERROR_NOT_FOUND), departmentId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorMessage);
        }
    }

}
