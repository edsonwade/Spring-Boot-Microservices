package code.with.vanilson.common.exception;

public class ErrorMessageGenerator {

    private ErrorMessageGenerator() {
        //default constructor
    }

    public static String generateDepartmentNotFoundMessage(String departmentName) {
        return departmentName;
    }

    public static String generateDepartmentBadRequestMessage(String departmentName) {
        return departmentName;
    }

    public static String generateEmployeeNotFoundMessage(String employeeName) {
        return employeeName;
    }
}
