package com.protobot.foroblog.exceptions.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelperCheckIfNullOrZeroLongException extends RuntimeException{

    Logger logger = LoggerFactory.getLogger(HelperCheckIfNullOrZeroLongException.class);

    public HelperCheckIfNullOrZeroLongException(){
        logger.error("AAAReceiving id with Zero or Null as value, can`t be processing the value");
    }


}
