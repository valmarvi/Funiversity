package com.switchfully.exception;

import com.switchfully.exception.exceptions.*;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger myLogger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AlreadyExistsException.class)
    public void usernameExistsException(AlreadyExistsException alreadyExistsException,
                                        HttpServletResponse response) throws IOException {
        myLogger.error("Unable to Add, Record Already Exists", alreadyExistsException);
        response.sendError(SC_BAD_REQUEST, alreadyExistsException.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public void setNotFoundException(NotFoundException notFoundException,
                                        HttpServletResponse response) throws IOException {
        myLogger.error("Unable to Find the Record", notFoundException);
        response.sendError(SC_BAD_REQUEST, notFoundException.getMessage());
    }

    @ExceptionHandler(UnauthorizatedException.class)
    public void unauthorizatedException(UnauthorizatedException unauthorizatedException,
                                        HttpServletResponse response) throws IOException {
        myLogger.error("You are not authorized to perform the request", unauthorizatedException);
        response.sendError(SC_UNAUTHORIZED, unauthorizatedException.getMessage());
    }

    @ExceptionHandler(UnknownUserException.class)
    public void unknownUserException(UnknownUserException unknownUserException,
                                     HttpServletResponse response) throws IOException {
        myLogger.error("The user is unknown to the system", unknownUserException);
        response.sendError(SC_UNAUTHORIZED, unknownUserException.getMessage());
    }

    @ExceptionHandler(WrongPasswordException.class)
    public void wrongPasswordException(WrongPasswordException wrongPasswordException,
                                       HttpServletResponse response) throws IOException {
        myLogger.error("Wrong password", wrongPasswordException);
        response.sendError(SC_UNAUTHORIZED, wrongPasswordException.getMessage());
    }
}
