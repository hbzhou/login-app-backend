package com.itsz.login.app.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {

    private int errorCode;
    private Object errorMessage;
    private LocalDateTime timestamp;


}
