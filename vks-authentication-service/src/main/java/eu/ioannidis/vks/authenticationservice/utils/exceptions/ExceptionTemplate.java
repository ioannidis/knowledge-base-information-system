package eu.ioannidis.vks.authenticationservice.utils.exceptions;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

public class ExceptionTemplate {

    private Date timestamp;

    private int code;

    private String status;

    private String message;

    public ExceptionTemplate() {
    }

    public ExceptionTemplate(Date timestamp, int code, String status, String message) {
        this.timestamp = timestamp;
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ExceptionTemplate{" +
                "timestamp=" + timestamp +
                ", code='" + code + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
