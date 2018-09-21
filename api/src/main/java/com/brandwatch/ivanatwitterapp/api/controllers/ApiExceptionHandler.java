package com.brandwatch.ivanatwitterapp.api.controllers;

import com.brandwatch.ivanatwitterapp.api.exceptions.EmptyHashTagException;
import com.brandwatch.ivanatwitterapp.api.exceptions.QueryDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(QueryDoesNotExistException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody()
    public String noSuchQueryId(QueryDoesNotExistException e) {
        return "Query id does not exist. Please enter a valid query id.";
    }

    @ExceptionHandler(EmptyHashTagException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String emptyHashTagAsInput(EmptyHashTagException e) {
        return "Hashtag can not be empty!";
    }
}