package com.protobot.foroblog.controller;

import com.protobot.foroblog.dto.response.RestResponse;
import com.protobot.foroblog.exceptions.controller.category.CategoryNotZeroIdException;
import com.protobot.foroblog.exceptions.controller.category.CategoryNullStringException;
import com.protobot.foroblog.helper.CheckIfNullOrEmptyString;
import com.protobot.foroblog.model.Category;
import com.protobot.foroblog.service.CategoryService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
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
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    @Mock
    CheckIfNullOrEmptyString checkIfNullOrEmptyString;

    @InjectMocks
    CategoryController controller;

    @Test
    void itShouldGetAllCategoriesWithEmptyList() {
        //given
        List<Category> list = new ArrayList<>();
        given(categoryService.getAllCategories()).willReturn(list);

        //when
        //List<Category> allCategories = categoryService.getAllCategories();
        controller.getAllCategories();

        //then
        then(categoryService).should().getAllCategories();
        then(categoryService).shouldHaveNoMoreInteractions();
        //assertEquals(0, allCategories.size());
        //assertNotNull(allCategories);
    }


    @Test
    void itShouldGetAllCategoriesWithNotEmptyList() {
        //given
        Category category = new Category(1L, "value");
        List<Category> list = new ArrayList<>();
        list.add(category);
        given(categoryService.getAllCategories()).willReturn(list);

        //when
        //List<Category> allCategories = categoryService.getAllCategories();
        controller.getAllCategories();

        //then
        then(categoryService).should().getAllCategories();
        then(categoryService).shouldHaveNoMoreInteractions();
        //assertEquals(1, allCategories.size());
    }

    @Test
    void itShouldGetCategoryByIdHappyCase() {
        //given
        Long valueToUse = 1L;
        Optional<Category> category = Optional.of(new Category(valueToUse, "value"));
        given(categoryService.getCategoryById(anyLong())).willReturn(category);

        //when
        controller.getCategoryById(valueToUse);

        //then
        then(categoryService).should().getCategoryById(anyLong());
        assertEquals(valueToUse, category.get().getId());
    }



    @Test
    @DisplayName("Doest`n work")
    void itShouldGetCategoryByIdWhenHaveZeroExecutingException() {
        //given
        //Long valueToUse = 0L;
        //Optional<Category> category = Optional.of(new Category(valueToUse, "value"));
        given(categoryService.getCategoryById(2L -1)).willThrow(CategoryNotZeroIdException.class);

        //when
        //controller.getCategoryById(valueToUse);
        assertThrows(CategoryNotZeroIdException.class, () -> controller.getCategoryById(2L -1));

        //then
        //then(categoryService).should().getCategoryById(anyLong());
    }

    @Test
    @Disabled
    void saveCategory() {
        //given
        Category category = new Category("value");
        given(categoryService.saveCategory(anyString())).willReturn(new Category(1L, category.getName()));

        //when
        controller.saveCategory(category);

        //then
        then(categoryService).should().saveCategory(anyString());
    }

    @Test
    void itShouldCheckIfNullHappyCase() {
        //given
        Category category = new Category("value");
        given(checkIfNullOrEmptyString.check(anyString())).willReturn(true);

        //when
        controller.saveCategory(category);

        //then
        then(checkIfNullOrEmptyString).should().check(anyString());
    }

    @Test
    void itShouldCheckIfEmptyNotHappyCase() {
        //given
        Category categoryEmpty = new Category("");
        given(checkIfNullOrEmptyString.check(anyString())).willReturn(false);

        //when
        assertThrows(CategoryNullStringException.class, () -> controller.saveCategory(categoryEmpty));

        //then
        then(checkIfNullOrEmptyString).should().check(categoryEmpty.getName());
    }

    @Test
    @Disabled
    void itShouldCheckIfNullNotHappyCase() {
        //given
        Category categoryNull = new Category(null);
        given(checkIfNullOrEmptyString.check(anyString())).willReturn(false);

        //when
        assertThrows(CategoryNullStringException.class, () -> controller.saveCategory(categoryNull));

        //then
        then(checkIfNullOrEmptyString).should().check(categoryNull.getName());
    }
}