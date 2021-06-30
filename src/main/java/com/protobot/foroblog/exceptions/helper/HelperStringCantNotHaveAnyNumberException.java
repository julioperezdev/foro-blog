package com.protobot.foroblog.exceptions.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelperStringCantNotHaveAnyNumberException extends NumberFormatException{

    Logger logger = LoggerFactory.getLogger(HelperStringCantNotHaveAnyNumberException.class);

    public HelperStringCantNotHaveAnyNumberException(String particularString) {
        logger.warn("Can not use a Number in this String :" + particularString);
        falseResult();
    }

    public boolean falseResult(){
        return false;
    }
}
