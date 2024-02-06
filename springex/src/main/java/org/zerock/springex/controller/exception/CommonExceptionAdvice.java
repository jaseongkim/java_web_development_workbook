package org.zerock.springex.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@Log4j2
@ControllerAdvice
public class CommonExceptionAdvice {

//    @ResponseBody
//    @ExceptionHandler(NumberFormatException.class)
//    public String exceptionNumber(NumberFormatException numberFormatException){
//        String message = numberFormatException.getMessage();
//        log.error("-------------------------------------");
//        log.error(message);
//        return "NUMBER FORMET EXCEPTION";
//    }

//    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptCommon(Exception exception) {
        StringBuffer buffer = new StringBuffer();

        buffer.append("<ul>");
        buffer.append("<li>" + exception.getMessage() + "<li>");
        Arrays.stream(exception.getStackTrace()).forEach(e -> {
            buffer.append("<li>" + e + "</li>");
        });
        buffer.append("</ul>");

        return buffer.toString();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(){

        return "custom404";
    }

}
