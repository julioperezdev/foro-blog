package com.protobot.foroblog.helper;

import org.springframework.stereotype.Service;

@Service
public class ConvertStringToLowerCaseHelper {

    public ConvertStringToLowerCaseHelper() {
    }

    public String convert(String particularString) {
        return particularString.toLowerCase();
    }
}
