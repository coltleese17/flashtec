package com.leesec.flashtractproject.exception;

public class DatabaseSaveFailedException extends RuntimeException {
    public DatabaseSaveFailedException(Throwable err){
        super(err);
    }
}
