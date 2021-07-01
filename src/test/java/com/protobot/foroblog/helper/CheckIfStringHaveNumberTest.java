package com.protobot.foroblog.helper;

import com.protobot.foroblog.exceptions.helper.HelperStringCantNotHaveAnyNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CheckIfStringHaveNumberTest {

    @InjectMocks
    CheckIfStringHaveNumber helper;

    @Test
    void itShouldCheckIfStringHaveNumberHappyCase() {
        //given
        String particularString = "Food";

        //when
        //then
        helper.check(particularString);
    }

    @Test
    void itShouldCheckIfStringHaveNumberWhenHaveNumberOnFirstChart() {
        //given
        String particularString = "1Food";

        //when
        //then
        assertThrows(HelperStringCantNotHaveAnyNumberException.class, () -> helper.check(particularString));
    }

    @Test
    void itShouldCheckIfStringHaveNumberWhenHaveNumberOnLastChart() {
        //given
        String particularString = "Food2";

        //when
        //then
        assertThrows(HelperStringCantNotHaveAnyNumberException.class, () -> helper.check(particularString));
    }

    @Test
    void itShouldCheckIfStringHaveNumberWhenHaveNumberOnBetweenOfCharts() {
        //given
        String particularString = "Fo3od";

        //when
        //then
        assertThrows(HelperStringCantNotHaveAnyNumberException.class, () -> helper.check(particularString));
    }

    @Test
    void itShouldCheckIfStringHaveNumberWhenHaveFullNumber() {
        //given
        String particularString = "1234";

        //when
        //then
        assertThrows(HelperStringCantNotHaveAnyNumberException.class, () -> helper.check(particularString));
    }
}