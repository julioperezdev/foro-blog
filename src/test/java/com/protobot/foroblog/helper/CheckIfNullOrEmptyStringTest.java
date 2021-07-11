package com.protobot.foroblog.helper;

import com.protobot.foroblog.exceptions.helper.HelperCheckIfNullOrEmptyStringException;
import org.junit.jupiter.api.Disabled;
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
    @Disabled
    void itShouldCheckIfNullHappyCase() {
        //given
        String particularString = "some";

        //when
        //assertThrows(HelperCheckIfNullOrEmptyStringException.class, () -> helper.check(particularString));

        //then
        //assertTrue(check);
    }

    @Test
    @Disabled
    void itShouldCheckIfNullWhenIsNull() {
        //given
        String particularString = null;

        //when
        //then
        assertThrows(HelperCheckIfNullOrEmptyStringException.class, () -> helper.check(particularString));

    }

    @Test
    @Disabled
    void itShouldCheckIfNullWhenIsEmpty() {
        //given
        String particularString = "";

        //when
        //then
        assertThrows(HelperCheckIfNullOrEmptyStringException.class, () -> helper.check(particularString));

    }
}