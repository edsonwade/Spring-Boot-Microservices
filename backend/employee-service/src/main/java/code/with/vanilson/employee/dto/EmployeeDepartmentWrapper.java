package code.with.vanilson.employee.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonPropertyOrder({"employee", "department"})
public class EmployeeDepartmentWrapper {
    private DepartmentDto department;
    private EmployeeDto employee;

    public EmployeeDepartmentWrapper() {
        // default constructors
    }

    public EmployeeDepartmentWrapper(DepartmentDto department, EmployeeDto employee) {
        this.department = department;
        this.employee = employee;
    }

}
