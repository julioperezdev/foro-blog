package com.protobot.foroblog.helper;

import com.protobot.foroblog.exceptions.helper.HelperCheckIfNullOrEmptyStringException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CheckIfNullOrEmptyString {

    Logger logger = LoggerFactory.getLogger(CheckIfNullOrEmptyString.class);

    public CheckIfNullOrEmptyString() {
    }

    public void check (String string){
        logger.info("Checking if Null or Empty this String");
        if(isNullOrEmpty(string))
            throw new HelperCheckIfNullOrEmptyStringException();
    }

    private boolean isNullOrEmpty(String string) {
        return !(string != null && !string.equals(""));
    }
}
