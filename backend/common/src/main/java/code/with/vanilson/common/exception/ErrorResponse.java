package code.with.vanilson.common.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    private String message;
    private HttpStatus status;
    private String zone;
    private int errorCode;

    public ErrorResponse(String message, HttpStatus status, String zone, int errorCode) {
        this.message = message;
        this.status = status;
        this.zone = zone;
        this.errorCode = errorCode;
    }
}