package com.protobot.foroblog.exceptions;



import com.protobot.foroblog.exceptions.helper.HelperCheckIfNullOrEmptyStringException;
import com.protobot.foroblog.exceptions.helper.HelperCheckIfNullOrZeroLongException;
import com.protobot.foroblog.exceptions.helper.HelperStringCantNotHaveAnyNumberException;
import org.springframework.http.HttpStatus;

public enum SupportedExceptions {

    //Categories Controller Exceptions
    //..
    //Blog Controller Exceptions
    //..

    //Helper Exceptions
    CATEGORY_BAD_PREREQUISITES(HelperCheckIfNullOrEmptyStringException.class, HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_BE_ZERO(HelperCheckIfNullOrZeroLongException.class, HttpStatus.PRECONDITION_FAILED),
    HELPER_STRING_CANT_HAVE_ANY_NUMBER(HelperStringCantNotHaveAnyNumberException.class, HttpStatus.PRECONDITION_FAILED);



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

