package code.with.vanilson.common.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private String message;
    private HttpStatus status;
    private String zone;
    private int errorCode;
    private LocalDateTime timestamp;

    public ErrorResponse(String message, HttpStatus status, String zone, int errorCode, LocalDateTime timestamp) {
        this.message = message;
        this.status = status;
        this.zone = zone;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
    }
}