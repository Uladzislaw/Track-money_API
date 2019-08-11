package com.serh.trackmoney.exception.handler;

import com.serh.trackmoney.exception.ApiErrorResponse;
import com.serh.trackmoney.exception.api.EmailNotValidException;
import com.serh.trackmoney.exception.api.NullableFieldException;
import com.serh.trackmoney.exception.api.PasswordsDontMatchException;
import com.serh.trackmoney.exception.api.UserAlreadyExistsException;
import com.serh.trackmoney.exception.api.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class GlobalApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> userExistsException(final Exception ex) {
        final ApiErrorResponse errorResponse
                = createApiException(HttpStatus.BAD_REQUEST, ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> userNotFound(final Exception ex) {
        final ApiErrorResponse errorResponse
                = createApiException(HttpStatus.NOT_FOUND, ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailNotValidException.class)
    public ResponseEntity<ApiErrorResponse> emailInvalid(final Exception ex) {
        final ApiErrorResponse errorResponse
                = createApiException(HttpStatus.BAD_REQUEST, ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordsDontMatchException.class)
    public ResponseEntity<ApiErrorResponse> passwordsDontMatch(final Exception ex) {
        final ApiErrorResponse errorResponse
                = createApiException(HttpStatus.BAD_REQUEST, ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullableFieldException.class)
    public ResponseEntity<ApiErrorResponse> nullableFields(final Exception ex) {
        final ApiErrorResponse errorResponse
                = createApiException(HttpStatus.BAD_REQUEST, ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex,
            final HttpHeaders headers,
            final HttpStatus status, final WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);

    }

    private ApiErrorResponse createApiException(final HttpStatus status,
                                                final Exception ex) {
        return ApiErrorResponse.builder()
                .error(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .build();
    }
}
