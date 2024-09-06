package com.MicroService.MicroServiceUsers.Infrastructure.Exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private List<String> messages;

    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(LocalDateTime timestamp, int status, String error, List<String> messages) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.messages = messages;
    }

    public ErrorResponse(int status, List<String> messages) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = HttpStatus.valueOf(status).getReasonPhrase();
        this.messages = messages;
    }
}
