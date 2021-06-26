package com.protobot.foroblog.exceptions.service.category;

import com.protobot.foroblog.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoryNullStringException extends RuntimeException{

    Logger logger = LoggerFactory.getLogger(CategoryNullStringException.class);

    public CategoryNullStringException (){
        logger.error("This category can`t be null to enroll in database because need set a Name");
    }
}
