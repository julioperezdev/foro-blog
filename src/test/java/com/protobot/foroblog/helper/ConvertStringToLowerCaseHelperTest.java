package com.protobot.foroblog.helper;

import com.protobot.foroblog.exceptions.helper.HelperStringCantNotHaveAnyNumberException;
import com.protobot.foroblog.model.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ConvertStringToLowerCaseHelperTest {

    @Mock
    CheckIfStringHaveNumber checkIfStringHaveNumber;

    @InjectMocks
    ConvertStringToLowerCaseHelper service;

    @Test
    void itShouldCheckIfConvertToLowerCaseAllLetterHaveUpperCaseHappyCase() {
        //given
        String oldString = "ABC";

        //when
        String stringConverted = service.convert(oldString);

        //then
        assertEquals("abc", stringConverted);
    }

    @Test
    void itShouldCheckIfConvertToLowerCaseSomeLetterHaveUpperCaseHappyCase() {
        //given
        String oldString = "AbCdeF";

        //when
        String stringConverted = service.convert(oldString);

        //then
        assertEquals("abcdef", stringConverted);
    }

    @Test
    void itShouldCheckIfConvertToLowerCaseAnyLetterHaveUpperCaseHappyCase() {
        //given
        String oldString = "zxcvbnm";

        //when
        String stringConverted = service.convert(oldString);

        //then
        assertEquals(oldString, stringConverted);
    }

    @Test
    void itShouldCheckIfConvertToLowerCaseHavingException() {
        //given
        String oldString = "zxFv2bnM";

        //when
        String stringConverted = service.convert(oldString);

        //then
        assertEquals("zxfv2bnm", stringConverted);
    }
}