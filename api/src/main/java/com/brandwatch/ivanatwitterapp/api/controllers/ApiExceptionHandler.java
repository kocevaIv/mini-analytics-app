package com.brandwatch.ivanatwitterapp.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.brandwatch.ivanatwitterapp.api.exceptions.EmptyQueryDefinition;
import com.brandwatch.ivanatwitterapp.api.exceptions.InvalidDateRangeException;
import com.brandwatch.ivanatwitterapp.api.exceptions.QueryDoesNotExistException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(QueryDoesNotExistException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody()
    public String noSuchQueryId(QueryDoesNotExistException e) {
        return "Query id does not exist. Please enter a valid query id.";
    }

    @ExceptionHandler(EmptyQueryDefinition.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String emptyHashTagAsInput(EmptyQueryDefinition e) {
        return "Hashtag can not be empty!";
    }

    @ExceptionHandler(InvalidDateRangeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String startDateIsAfterEndDate(InvalidDateRangeException e) {
        return "Start date must be before end date";
    }
}