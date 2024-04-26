package code.with.vanilson.department.service;

import code.with.vanilson.department.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> findAllDepartments();

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto findDepartmentById(long departmentId);

    List<DepartmentDto> saveDepartments(List<DepartmentDto> departmentDtos);

    DepartmentDto updateDepartment(DepartmentDto departmentDto);

    DepartmentDto  getDepartmentByCode(String code);

    void deleteDepartment(long departmentId);
}
