package com.protobot.foroblog.controller;

import com.protobot.foroblog.exceptions.controller.category.CategoryNotZeroIdException;
import com.protobot.foroblog.exceptions.controller.category.CategoryNullStringException;
import com.protobot.foroblog.helper.CheckIfNullOrEmptyString;
import com.protobot.foroblog.model.Category;
import com.protobot.foroblog.service.CategoryService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    @Mock
    CheckIfNullOrEmptyString checkIfNullOrEmptyString;

    @InjectMocks
    CategoryController controller;

    MockMvc mockMvc;

    List<Category> categories = new ArrayList<>();
    Category categoryA = new Category(1L, "value1");
    Category categoryB = new Category(2L, "value2");
    Category categoryC = new Category(3L, "value3");

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

    @Nested
    public class TestListHttpResponseHappyCase{
        List<Category> categories = new ArrayList<>();
        Category categoryA = new Category(1L, "value1");
        Category categoryB = new Category(2L, "value2");
        Category categoryC = new Category(3L, "value3");

        @BeforeEach
        void setUp() {
            categories.add(categoryA);
            categories.add(categoryB);
            categories.add(categoryC);

            mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        }

        @Test
        void itShouldGetAllCategoriesHttpResponse() throws Exception {
            //given
            given(categoryService.getAllCategories()).willReturn(categories);

            //then
            mockMvc.perform(get("/api/v1/category"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.status", is("OK")))
                    .andExpect(jsonPath("$.body", hasSize(3)))
                    .andExpect(jsonPath("$.body[0].id", is(categoryA.getId().intValue())));

        }
    }

    @Test
    void itShouldGetAllCategoriesWithEmptyList() {
        //given
        List<Category> list = new ArrayList<>();
        given(categoryService.getAllCategories()).willReturn(list);

        //when
        controller.getAllCategories();

        //then
        then(categoryService).should().getAllCategories();
        then(categoryService).shouldHaveNoMoreInteractions();
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
        given(categoryService.saveCategory(category.getName())).willReturn(new Category(1L, category.getName()));

        //when
        controller.saveCategory(category);

        //then
        //then(categoryService).should().saveCategory(anyString());

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
    void itShouldCheckIfNull() {
        //given
        Category categoryNull = new Category(null);

        //when
        assertThrows(CategoryNullStringException.class, () -> controller.saveCategory(categoryNull));

        //then
        then(checkIfNullOrEmptyString).should().check(categoryNull.getName());
    }

    @Test
    void itShouldDeleteCategoryByIdHappyCase() {
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
    void itShouldDoesDeleteCategoryByIdWithException() {
        //given
        Long id = 0L;

        //when
        //then
        then(categoryService).shouldHaveZeroInteractions();
        assertThrows(CategoryNotZeroIdException.class, () -> controller.deleteCategoryById(id));
    }
}