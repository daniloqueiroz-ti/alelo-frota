package br.com.alelofrota.api.exception;

import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* -------------------- ROLE EXCEPTION -------------------- */

    @ExceptionHandler(RoleException.class)
    public ProblemDetail handleRoleException(RoleException ex, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (ex.getMessage().toLowerCase().contains("not found")) {
            status = HttpStatus.NOT_FOUND;
        }

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        problem.setTitle("Business rule violation");
        problem.setProperty("path", request.getRequestURI());

        return problem;
    }

    /* -------------------- @VALID (REQUEST BODY) -------------------- */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationErrors(MethodArgumentNotValidException ex,
                                                HttpServletRequest request) {

        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                errors
        );

        problem.setTitle("Validation failed");
        problem.setProperty("path", request.getRequestURI());

        return problem;
    }

    /* -------------------- @VALID (PATH / PARAM) -------------------- */

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolation(ConstraintViolationException ex,
                                                   HttpServletRequest request) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );

        problem.setTitle("Constraint violation");
        problem.setProperty("path", request.getRequestURI());

        return problem;
    }

    /* -------------------- FALLBACK -------------------- */

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex,
                                                HttpServletRequest request) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected internal error"
        );

        problem.setTitle("Internal Server Error");
        problem.setProperty("path", request.getRequestURI());

        return problem;
    }
}
