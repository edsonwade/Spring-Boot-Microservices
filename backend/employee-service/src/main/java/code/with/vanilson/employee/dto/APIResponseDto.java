package code.with.vanilson.employee.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonPropertyOrder({"employeeDto", "departmentDto"})
public class APIResponseDto {
    DepartmentDto departmentDto;
    EmployeeDto employeeDto;

    public APIResponseDto() {
        // empty constructor
    }

}
