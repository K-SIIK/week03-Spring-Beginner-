package com.sparta.assignment.controller;

import com.sparta.assignment.entity.TimeStamped;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@ResponseStatus(value = HttpStatus.NOT_FOUND)
// 클라이언트에게 200(정상으로)이 아닌 404임을 알림
// 200 으로 뜨면 안된다고 하셨는데 왜지?
public class GlobalExceptionHandler extends TimeStamped {
    @ExceptionHandler(value = IllegalArgumentException.class)
    public String handlerIllegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }

    @ExceptionHandler(value = NullPointerException.class)
    public String handlerNullPointerException(NullPointerException e) {
        return e.getMessage();
    }
}
