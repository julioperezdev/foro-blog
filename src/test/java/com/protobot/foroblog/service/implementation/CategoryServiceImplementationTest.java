package com.protobot.foroblog.service.implementation;

import com.protobot.foroblog.model.Category;
import com.protobot.foroblog.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplementationTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryServiceImplementation service;

    @Test
    void itShouldGetAllCategoriesHappyCase() {
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
}