package eu.ioannidis.vks.authenticationservice.utils.exceptions.advices;

import eu.ioannidis.vks.authenticationservice.utils.exceptions.ExceptionTemplate;
import org.hibernate.JDBCException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.sql.Timestamp;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {JDBCException.class})
    protected ResponseEntity<Object> handlePSQLConflict(JDBCException ex, WebRequest request) {

        SQLException e = ex.getSQLException();

        if (e instanceof PSQLException) {
            ExceptionTemplate exceptionTemplate = new ExceptionTemplate( new Timestamp(System.currentTimeMillis()), e.getErrorCode(), e.getSQLState(), e.getMessage());

            return handleExceptionInternal(ex, exceptionTemplate, new HttpHeaders(), HttpStatus.CONFLICT, request);
        }

        String bodyOfResponse = "Unknown Error!";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionTemplate exceptionTemplate = new ExceptionTemplate( new Timestamp(System.currentTimeMillis()), HttpStatus.UNPROCESSABLE_ENTITY.value() , "", ex.getMessage());

        return handleExceptionInternal(ex, exceptionTemplate, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }
}
