package cl.arellano.wallmart.wallmartbackend.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvicer extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value =  SearchException.class)
    protected ResponseEntity<String> handleSearchException(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ((SearchException)ex).getErrorCode();
        if(ErrorCodes.NO_RESULTS.equals(((SearchException)ex).getErrorCode())) {
            return new ResponseEntity<>(bodyOfResponse, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<String> handleUnexpectedException(
            RuntimeException ex, WebRequest request) {
        log.info("Unexpected error", ex);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
