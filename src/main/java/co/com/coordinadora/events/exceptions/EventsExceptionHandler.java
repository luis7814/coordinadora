package co.com.coordinadora.events.exceptions;

import co.com.coordinadora.events.object.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EventsExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public @ResponseBody ErrorMessage handleBadRequestException(BadRequestException ex) {
        return new ErrorMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException .class)
    public @ResponseBody ErrorMessage handleNotFoundException(NotFoundException ex) {
        return new ErrorMessage(ex.getMessage());
    }
}
