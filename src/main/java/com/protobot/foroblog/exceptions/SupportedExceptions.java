package com.protobot.foroblog.exceptions;

import com.protobot.foroblog.exceptions.service.category.CategoryNullStringException;
import org.springframework.http.HttpStatus;

public enum SupportedExceptions {

    CATEGORY_BAD_PREREQUISITES(CategoryNullStringException.class, HttpStatus.PRECONDITION_FAILED);
    //AUTHOR_NOT_FOUND(AuthorDoesNotExists.class, HttpStatus.NOT_FOUND);

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

