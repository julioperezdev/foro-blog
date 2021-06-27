package com.protobot.foroblog.exceptions.controller.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoryNotZeroIdException extends RuntimeException{

    Logger logger = LoggerFactory.getLogger(CategoryNotZeroIdException.class);

    public CategoryNotZeroIdException (){
        logger.error("Receiving id with Zero as value, can`t be zero to find in database the category");
    }

}
