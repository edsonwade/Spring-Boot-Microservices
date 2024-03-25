package code.with.vanilson.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class DepartmentDto {
    private long departmentId;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;

    public DepartmentDto() {
        // default constructors
    }

}
