
package com.demo_project.exceptionHandling;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class globalExceptionHandler extends Exception
{
    @ExceptionHandler
    public String exceptionHandlerandler(Exception e)
    {
    	return e.getMessage();
    }
}
