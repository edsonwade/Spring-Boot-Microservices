package code.with.vanilson.employee;

import lombok.Data;

@Data
public class EmployeeDto {
    private long employeeId;
    private String firstName;
    private String lastName;
    private String email;

    public EmployeeDto() {
        //default constructor
    }

    public EmployeeDto(long employeeId, String firstName, String lastName, String email) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
