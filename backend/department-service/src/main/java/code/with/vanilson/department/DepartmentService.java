package code.with.vanilson.department;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> findAllDepartments();

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto findDepartmentById(long departmentId);

    List<DepartmentDto> saveDepartments(List<DepartmentDto> departmentDtos);

    DepartmentDto updateDepartment(DepartmentDto departmentDto);

    void deleteDepartment(long departmentId);
}
