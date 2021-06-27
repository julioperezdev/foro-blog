package com.protobot.foroblog.controller;

import com.protobot.foroblog.dto.response.RestResponse;
import com.protobot.foroblog.exceptions.controller.category.CategoryNotZeroIdException;
import com.protobot.foroblog.exceptions.controller.category.CategoryNullStringException;
import com.protobot.foroblog.helper.CheckIfNullOrEmptyString;
import com.protobot.foroblog.model.Category;
import com.protobot.foroblog.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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

    @Captor
    ArgumentCaptor<Long> longArgumentCaptor;
/*
    @BeforeEach
    void setUp() {
        given(categoryService.getCategoryById(longArgumentCaptor.capture()))
                .willAnswer(invocation -> {
                    Optional<Category> category = Optional.of(new Category());

                    Long idCategory = invocation.getArgument(0);

                    if(idCategory.equals(0L)){
                        throw new CategoryNotZeroIdException();
                    }
                    else if(idCategory > 0L){
                        return category.get();
                    }
                    throw new RuntimeException("Invalid argument");
                });
    }


 */



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
    void itShouldGetCategoryByIdWhenHaveZeroExecutingException() {
        //given
        Long valueToUse = 0L;

        //when
        //then
        then(categoryService).shouldHaveZeroInteractions();
        assertThrows(CategoryNotZeroIdException.class, () -> controller.getCategoryById(valueToUse));
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

    @Test
    void itShouldDeleteCategoryById() {
        //given
        Long id = 2L;
        given(categoryService.deleteCategoryById(anyLong())).willReturn(true);

        //when
        controller.deleteCategoryById(id);

        //then
        then(categoryService).should(times(1)).deleteCategoryById(id);
        assertTrue(categoryService.deleteCategoryById(id));
    }

    @Test
    void itShouldDoesExceptionWithDeleteCategoryById() {
        //given
        Long id = 0L;

        //when
        //then
        then(categoryService).shouldHaveZeroInteractions();
        assertThrows(CategoryNotZeroIdException.class, () -> controller.deleteCategoryById(id));
    }
}