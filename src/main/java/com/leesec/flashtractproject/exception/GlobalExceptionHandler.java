package com.leesec.flashtractproject.exception;

import com.leesec.flashtractproject.error.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(ContractNotFoundException.class)
        @ResponseStatus(HttpStatus.NO_CONTENT)
        @ResponseBody
        public ResponseEntity<ResponseError> handleContractNotFoundException(ContractNotFoundException exception) {
                //       TODO:
                //       Figure out why this code is not returning a message
        return new ResponseEntity<ResponseError>(
          new ResponseError("Contract not found with given ID"), HttpStatus.valueOf(204));
    }


}


//public class AppExceptionControllerAdvice {
//
//    @ExceptionHandler(AppException.class)
//    public ResponseEntity<ApplicationErrorDTO> handleAppException(AppException exception) {
//        return new ResponseEntity<ApplicationErrorDTO>(
//                new ApplicationErrorDTO(exception.getCode(), exception.getMessage()), exception.getHttpStatus());
//    }
//}