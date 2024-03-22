package code.with.vanilson.department.dto;

import lombok.Data;

@Data
public class DepartmentDto {
    private long departmentId;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;

    public DepartmentDto() {
        //default constructor
    }

    public DepartmentDto(long departmentId, String departmentName, String departmentDescription,
                         String departmentCode) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentDescription = departmentDescription;
        this.departmentCode = departmentCode;
    }
}
