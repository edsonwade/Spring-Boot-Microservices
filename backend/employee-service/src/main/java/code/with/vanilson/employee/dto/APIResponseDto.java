package code.with.vanilson.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class APIResponseDto {
    DepartmentDto departmentDto;
    EmployeeDto employeeDto;

    public APIResponseDto() {
    }

}
