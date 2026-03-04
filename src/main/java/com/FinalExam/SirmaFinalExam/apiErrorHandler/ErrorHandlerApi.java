package com.FinalExam.SirmaFinalExam.apiErrorHandler;

import com.FinalExam.SirmaFinalExam.Dtos.ErrorDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.rmi.ServerError;
import java.time.LocalDateTime;

public class ErrorHandlerApi {

    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorDto handleEntityNotFoundException(EntityNotFoundException e) {
        return new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto handleMethodArgumentNotFoundException(MethodArgumentNotValidException e) {
        return new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ErrorDto badRequestExceptionHandler(HttpClientErrorException.BadRequest e) {
        return new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(ServerError.class)
    public ErrorDto serverErrorHandler(ServerError e) {
        return new ErrorDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
    }


}
