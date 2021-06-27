package com.protobot.foroblog.helper;

import org.springframework.stereotype.Service;

@Service
public class CheckIfNullOrEmptyString {

    public CheckIfNullOrEmptyString() {
    }

    public boolean check (String string){
        return string != null && !string.equals("");
    }
}
