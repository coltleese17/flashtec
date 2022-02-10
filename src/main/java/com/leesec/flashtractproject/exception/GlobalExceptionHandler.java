package com.leesec.flashtractproject.exception;

import com.leesec.flashtractproject.error.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(ContractNotFoundException.class)
        public ResponseEntity<ResponseError> handleContractNotFoundException(ContractNotFoundException exception) {
               return new ResponseEntity<ResponseError>(
                  new ResponseError("Contract not found with given ID"), HttpStatus.valueOf(200));
        }

        @ExceptionHandler(NoContractFoundForInvoiceContractIDException.class)
        public ResponseEntity<ResponseError> handleNoContractFoundForInvoiceContractIDException(NoContractFoundForInvoiceContractIDException exception) {
                return new ResponseEntity<ResponseError>(
                        new ResponseError("Could not create invoice, no contract found for given contract ID"), HttpStatus.valueOf(200));
        }

        @ExceptionHandler(InvoiceCostExceedsContractLimitException.class)
        public ResponseEntity<ResponseError> handleInvoiceCostExceedsContractLimitException(InvoiceCostExceedsContractLimitException exception) {
                return new ResponseEntity<ResponseError>(
                        new ResponseError("Could not apply invoice to contract, invoice cost exceeds remaining contract balance"), HttpStatus.valueOf(200));
        }

        @ExceptionHandler(DatabaseSaveFailedException.class)
        public ResponseEntity<ResponseError> handleDatabaseSaveFailedException(DatabaseSaveFailedException exception) {
                return new ResponseEntity<ResponseError>(
                        new ResponseError("Internal Issue: Invoice Not Created"), HttpStatus.valueOf(500));
        }

        @ExceptionHandler(InvoiceNotFoundException.class)
        public ResponseEntity<ResponseError> handleInvoiceNotFoundException(InvoiceNotFoundException exception) {
                return new ResponseEntity<ResponseError>(
                        new ResponseError("Invoice not found with given ID"), HttpStatus.valueOf(200));
        }

        @ExceptionHandler(NoInvoicesForThisContractIDException.class)
        public ResponseEntity<ResponseError> handleNoInvoiceForThisContractIDException(NoInvoicesForThisContractIDException exception) {
                return new ResponseEntity<ResponseError>(
                        new ResponseError("No invoices found for this contract ID"), HttpStatus.valueOf(200));
        }
}