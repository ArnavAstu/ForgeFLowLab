package com.example.forgeflowlab.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


// Handles exceptions thrown by all REST Controllers globally
@RestControllerAdvice
public class GlobalExceptionHandler {


    // Tells Spring WHICH exception this method should handle.
    // If TaskNotFoundException occurs,
    // Spring will call handleTaskNotFound().
    @ExceptionHandler(TaskNotFoundException.class)


    // Tells Spring WHAT HTTP status to return to the client.
    // Since the requested Task does not exist,
    // we return HTTP 404 NOT_FOUND.
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTaskNotFound(TaskNotFoundException ex) {

        // 'ex' contains the exception that occurred.
        // getMessage() gets the message we provided
        // when throwing TaskNotFoundException.
        //
        // Example:
        // "Task not found with id: 99"
        return ex.getMessage();
    }
}