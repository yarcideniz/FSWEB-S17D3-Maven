package com.workintech.zoo.exceptions;

import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ZooException extends RuntimeException{

    private HttpStatus httpStatus;

    public ZooException(String message) {
        super(message);
    }
    public ZooException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }


}
