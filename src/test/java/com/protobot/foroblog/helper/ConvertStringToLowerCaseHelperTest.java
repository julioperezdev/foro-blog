package com.protobot.foroblog.helper;

import com.protobot.foroblog.model.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConvertStringToLowerCaseHelperTest {

    @InjectMocks
    ConvertStringToLowerCaseHelper service;

    @Test
    void itShouldCheckIfConvertToLowerCaseHappyCase() {
        //given
        String oldString = "321";

        //when
        String stringConverted = service.convert(oldString);

        //then
        assertEquals("321", stringConverted);
    }
}