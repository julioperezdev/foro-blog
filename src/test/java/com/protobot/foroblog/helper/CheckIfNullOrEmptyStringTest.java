package com.protobot.foroblog.helper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CheckIfNullOrEmptyStringTest {

    @InjectMocks
    CheckIfNullOrEmptyString helper;

    @Test
    void itShouldCheckIfNullHappyCase() {
        //given
        String particularString = "some";

        //when
        boolean check = helper.check(particularString);

        //then
        assertTrue(check);
    }

    @Test
    void itShouldCheckIfNullWhenIsNull() {
        //given
        String particularString = null;

        //when
        boolean check = helper.check(particularString);

        //then
        assertFalse(check);
    }

    @Test
    void itShouldCheckIfNullWhenIsEmpty() {
        //given
        String particularString = "";

        //when
        boolean check = helper.check(particularString);

        //then
        assertFalse(check);
    }
}