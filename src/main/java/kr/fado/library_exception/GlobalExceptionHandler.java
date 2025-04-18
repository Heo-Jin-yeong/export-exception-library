package kr.fado.library_exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import javax.crypto.NoSuchPaddingException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<?> handleInternalServerError(Exception e) {

        log.error("Internal Server Error - {}", NestedExceptionUtils.getMostSpecificCause(e).getMessage(), e);

        return ResponseEntity
                .status(BasicExceptionEnum.INTERNAL_SERVER_ERROR.getStatus())
                .body(BasicExceptionResponse.builder()
                        .errorCode(BasicExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
                        .errorMessage(BasicExceptionEnum.INTERNAL_SERVER_ERROR.getMessage())
                        .build()
                );
    }

    @ExceptionHandler({
            NoHandlerFoundException.class,
            NoResourceFoundException.class,
    })
    public ResponseEntity<?> handleNotFoundException(Exception e) {

        log.error("Resource Not Found - {}", NestedExceptionUtils.getMostSpecificCause(e).getMessage(), e);

        return ResponseEntity
                .status(BasicExceptionEnum.NOT_FOUND.getStatus())
                .body(BasicExceptionResponse.builder()
                        .errorCode(BasicExceptionEnum.NOT_FOUND.getCode())
                        .errorMessage(BasicExceptionEnum.NOT_FOUND.getMessage())
                        .build()
                );
    }

    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class
    })
    public ResponseEntity<?> handleMethodNotSupportedException(Exception e) {

        log.error("Method Not Supported - {}", NestedExceptionUtils.getMostSpecificCause(e).getMessage(), e);

        return ResponseEntity
                .status(BasicExceptionEnum.METHOD_NOT_ALLOWED.getStatus())
                .body(BasicExceptionResponse.builder()
                        .errorCode(BasicExceptionEnum.METHOD_NOT_ALLOWED.getCode())
                        .errorMessage(BasicExceptionEnum.METHOD_NOT_ALLOWED.getMessage())
                        .build()
                );
    }

    @ExceptionHandler({
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            InvalidParameterException.class,
            IllegalArgumentException.class,
            ConstraintViolationException.class,
    })
    public ResponseEntity<?> handleBadRequestException(Exception e) {

        log.error("Invalid Parameter - {}", NestedExceptionUtils.getMostSpecificCause(e).getMessage(), e);

        return ResponseEntity
                .status(BasicExceptionEnum.INVALID_PARAMETER.getStatus())
                .body(BasicExceptionResponse.builder()
                        .errorCode(BasicExceptionEnum.INVALID_PARAMETER.getCode())
                        .errorMessage(BasicExceptionEnum.INVALID_PARAMETER.getMessage())
                        .build()
                );
    }

    @ExceptionHandler({
            NoSuchPaddingException.class,
            NoSuchAlgorithmException.class,
    })
    public ResponseEntity<?> handleLoginException(Exception e) {

        log.error("Login Failed - {}", NestedExceptionUtils.getMostSpecificCause(e).getMessage(), e);

        return ResponseEntity
                .status(BasicExceptionEnum.INTERNAL_SERVER_ERROR.getStatus())
                .body(BasicExceptionResponse.builder()
                        .errorCode(BasicExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
                        .errorMessage("Login Failed")
                        .build()
                );
    }

    @ExceptionHandler({
            BasicCustomException.class
    })
    public ResponseEntity<?> handleBasicCustomException(BasicCustomException ce) {

        log.error("Custom Exception - {}", ce.getMessage(), ce);

        return ResponseEntity
                .status(ce.getStatus())
                .body(BasicExceptionResponse.builder()
                        .errorCode(ce.getCode())
                        .errorMessage(ce.getMessage())
                        .build()
                );
    }
}
