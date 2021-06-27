package com.protobot.foroblog.exceptions;

import com.protobot.foroblog.exceptions.controller.category.CategoryNotZeroIdException;
import com.protobot.foroblog.exceptions.controller.category.CategoryNullStringException;
import org.springframework.http.HttpStatus;

public enum SupportedExceptions {

    CATEGORY_BAD_PREREQUISITES(CategoryNullStringException.class, HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_BE_ZERO(CategoryNotZeroIdException.class, HttpStatus.PRECONDITION_FAILED);

    private Class<? extends Throwable> exceptionClass;
    private HttpStatus status;
    private SupportedExceptions(Class<? extends Throwable>exception, HttpStatus status){
        this.exceptionClass = exception;
        this.status = status;
    }

    public Class <? extends Throwable> getExceptionClass(){
        return this.exceptionClass;
    }

    public HttpStatus getStatus(){
        return this.status;
    }
}

