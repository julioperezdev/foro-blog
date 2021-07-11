package com.protobot.foroblog.service.implementation;

import com.protobot.foroblog.exceptions.helper.HelperCheckIfNullOrEmptyStringException;
import com.protobot.foroblog.helper.CheckIfNullOrEmptyString;
import com.protobot.foroblog.helper.CheckIfNullOrZeroLong;
import com.protobot.foroblog.helper.ConvertStringToLowerCaseHelper;
import com.protobot.foroblog.model.Category;
import com.protobot.foroblog.repository.CategoryRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplementationTest {

    @Mock
    ConvertStringToLowerCaseHelper convertStringToLowerCaseHelper;

    @Mock
    CheckIfNullOrEmptyString checkIfNullOrEmptyString;

    @Mock
    CheckIfNullOrZeroLong checkIfNullOrZeroLong;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryServiceImplementation service;

    @Test
    void itShouldGetAllCategoriesWithZeroSizeHappyCase() {
        //given
        List<Category> allCategories = new ArrayList<>();
        given(categoryRepository.findAll()).willReturn(allCategories);

        //when
        service.getAllCategories();

        //then
        then(categoryRepository).should().findAll();
        assertNotNull(allCategories);
        assertEquals( 0, allCategories.size());
    }

    @Test
    void itShouldGetAllCategoriesWithOneSizeHappyCase() {
        //given
        Category category = new Category();
        List<Category> allCategories = new ArrayList<>();
        allCategories.add(category);
        given(categoryRepository.findAll()).willReturn(allCategories);

        //when
        service.getAllCategories();

        //then
        then(categoryRepository).should().findAll();
        assertNotNull(allCategories);
        assertEquals( 1, allCategories.size());
    }

    @Test
    @Disabled
    void itShouldSaveCategoryHappyCase() {
        //given
        Category categoryBefore = new Category("ai");
        Category categoryAfter = new Category("ai");
        given(convertStringToLowerCaseHelper.convert(anyString()))
                .willReturn(categoryAfter.getName());
        given(categoryRepository.saveCategory(anyString()))
                .willReturn(new Category(1L, categoryAfter.getName()));

        //when
        Category categoryCreated = categoryRepository.saveCategory(categoryBefore.getName());

        //then
        then(convertStringToLowerCaseHelper).should().convert(categoryBefore.getName());
        then(categoryRepository).should().saveCategory(categoryAfter.getName());
        //assertNotNull(categoryCreated);
    }

    @Test
    @Disabled
    void itShouldCheckIfEmptyNotHappyCase() {
        //given
        Category categoryEmpty = new Category("");
        //given(checkIfNullOrEmptyString.check(anyString())).willReturn(false);

        //when
        assertThrows(HelperCheckIfNullOrEmptyStringException.class, () -> service.saveCategory(categoryEmpty.getName()));

        //then
        //then(checkIfNullOrEmptyString).should().check(categoryEmpty.getName());
    }

    @Test
    @Disabled
    void itShouldCheckIfNull() {
        //given
        Category categoryNull = new Category(null);

        //when
        assertThrows(HelperCheckIfNullOrEmptyStringException.class, () -> service.saveCategory(categoryNull.getName()));

        //then
        then(checkIfNullOrEmptyString).should().check(categoryNull.getName());
    }

    @Test
    void itShouldGetTheFirstCategoryById() {
        //given

        Category categoryOne = new Category(1L, "it");
        Category categoryTwo = new Category(2L, "wallets");
        List<Category> list = new ArrayList<>();
        list.add(categoryOne);
        list.add(categoryTwo);
        given(categoryRepository.findAll()).willReturn(list);

        //when
        Optional<Category> result = service.getCategoryById(1L);

        //then
        then(categoryRepository).should().findAll();
        then(categoryRepository).shouldHaveNoMoreInteractions();
        assertTrue(result.isPresent());
        assertEquals(categoryOne, result.get());
    }

}