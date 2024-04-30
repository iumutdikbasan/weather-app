package com.iumutdikbasan.weatherapp.exception.globalExceptionHandler;
import com.iumutdikbasan.weatherapp.exception.exceptions.MyException;
import com.iumutdikbasan.weatherapp.kafka.KafkaService;
import com.iumutdikbasan.weatherapp.result.generalResult.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final KafkaService kafkaService;

    @ExceptionHandler({MyException.class})
    public ResponseEntity<Result> notfound(MyException exception){
        String record = String.format("[%s:%d] Error occurred : %s", getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), exception.getMessage());
        kafkaService.sendMessage(record,"warn");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result(false,exception.getMessage()));
    }



}