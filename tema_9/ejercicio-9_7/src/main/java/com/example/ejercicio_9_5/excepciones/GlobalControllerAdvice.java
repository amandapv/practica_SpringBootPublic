package com.example.ejercicio_9_5.excepciones;

import org.springframework.http.HttpHeaders; 

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(CursoNotFoundException.class)
    public ResponseEntity<?> handleCursoNotFound(CursoNotFoundException ex, WebRequest request) {

        ExceptionBody body = new ExceptionBody(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
    }

    
    @ExceptionHandler(EmptyCursosException.class)
    public ResponseEntity<?> handleEmptyCursos(EmptyCursosException ex, WebRequest request) {

        ExceptionBody body = new ExceptionBody(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ExceptionBody myBody = new ExceptionBody(status, ex.getMessage());
        return ResponseEntity.status(status).headers(headers).body(myBody);
    }
}


@AllArgsConstructor
@Data
class ExceptionBody { //solo voy a meter en el cuerpo del mensaje de error estos 2 datos...
    private HttpStatusCode status;
    private String message;
}