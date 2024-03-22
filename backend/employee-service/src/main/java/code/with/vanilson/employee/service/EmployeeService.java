package code.with.vanilson.employee.service;

import code.with.vanilson.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAllEmployees();

    EmployeeDto saveEmployeeDto(EmployeeDto employeeDto);

    EmployeeDto findEmployeeById(long employeeId);

    EmployeeDto findEmployeeByEmail(String employeeIEmail);

    EmployeeDto updateEmployeeDto(EmployeeDto employeeDto);

    void deleteEmployee(long employeeId);
}
