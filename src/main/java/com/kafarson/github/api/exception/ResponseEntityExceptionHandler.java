package com.kafarson.github.api.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class ResponseEntityExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ResponseErrorInfo> handleNotFoundException(NotFoundException exception) {
        ResponseErrorInfo responseErrorInfo = ResponseErrorInfo.builder()
                .message(exception.getMessage())
                .fields(exception.getFields())
                .build();
        log.error(exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseErrorInfo);
    }

    @ExceptionHandler({SystemException.class})
    public ResponseEntity<ResponseErrorInfo> handleSystemException(SystemException exception) {
        ResponseErrorInfo responseErrorInfo = ResponseErrorInfo.builder()
                .message(exception.getMessage())
                .fields(exception.getFields())
                .details(exception.getDetails())
                .build();
        log.error(exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseErrorInfo);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ResponseErrorInfo> handleBadRequestException(BadRequestException exception) {
        ResponseErrorInfo responseErrorInfo = ResponseErrorInfo.builder()
                .message(exception.getMessage())
                .fields(exception.getFields())
                .build();
        log.error(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(responseErrorInfo);
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ResponseErrorInfo> handleAuthenticationException(AuthenticationException exception) {
        ResponseErrorInfo responseErrorInfo = ResponseErrorInfo.builder()
                .message(exception.getMessage())
                .fields(exception.getFields())
                .build();
        log.error(exception);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(responseErrorInfo);
    }
}
