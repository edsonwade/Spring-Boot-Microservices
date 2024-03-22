package code.with.vanilson.message;

public final class ErrorMessageConstant {

    // Not Found Messages
    public static final String RESOURCE_NOT_FOUND = "Resource not found";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String PRODUCT_NOT_FOUND = "Product not found";

    // Invalid Messages
    public static final String INVALID_REQUEST = "Invalid request";
    public static final String INVALID_CREDENTIALS = "Invalid credentials";
    public static final String INVALID_INPUT = "Invalid input";

    // Denied Messages
    public static final String ACCESS_DENIED = "Access denied";
    public static final String PERMISSION_DENIED = "Permission denied";

    // Conflict Messages
    public static final String CONFLICT_ERROR = "Conflict error";
    public static final String DUPLICATE_ENTRY = "Duplicate entry";

    // Server Error Messages
    public static final String INTERNAL_SERVER_ERROR = "Internal server error";
    public static final String DATABASE_ERROR = "Database error";
    public static final String UNEXPECTED_ERROR = "Unexpected error";

    // Validation Error Messages
    public static final String VALIDATION_ERROR = "Validation error";
    public static final String FIELD_REQUIRED = "Field is required";
    public static final String INVALID_FORMAT = "Invalid format";

    // Authentication Error Messages
    public static final String AUTHENTICATION_ERROR = "Authentication error";
    public static final String TOKEN_EXPIRED = "Token expired";
    public static final String SESSION_EXPIRED = "Session expired";

    // Client Error Messages
    public static final String CLIENT_ERROR = "Client error";
    public static final String BAD_REQUEST = "Bad request";
    public static final String UNSUPPORTED_MEDIA_TYPE = "Unsupported media type";

    // Success Messages
    public static final String OPERATION_SUCCESSFUL = "Operation successful";
    public static final String LOGIN_SUCCESSFUL = "Login successful";
    public static final String LOGOUT_SUCCESSFUL = "Logout successful";

    // Employee Service Error Messages
    public static final String EMPLOYEE_NOT_FOUND = "Employee not found";
    public static final String EMPLOYEE_ALREADY_EXISTS = "Employee already exists";
    public static final String EMPLOYEE_INVALID_DATA = "Invalid employee data";

    // Department Service Error Messages
    public static final String DEPARTMENT_NOT_FOUND = "Department not found";
    public static final String DEPARTMENT_ALREADY_EXISTS = "Department already exists";
    public static final String DEPARTMENT_INVALID_DATA = "Invalid department data";

    // General Error Messages


    // Private constructor to prevent instantiation
    private ErrorMessageConstant() {
        throw new AssertionError("Cannot instantiate ErrorMessageConstants");
    }
}
