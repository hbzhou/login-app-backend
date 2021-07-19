package com.itsz.login.app.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {

    private int status;
    private String error;
    private String path;
    private LocalDateTime timestamp;

}
