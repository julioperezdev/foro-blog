package com.protobot.foroblog.service.implementation;

import com.protobot.foroblog.model.Blog;
import com.protobot.foroblog.repository.BlogRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class BlogServiceImplementationTest {

    @Mock
    BlogRepository blogRepository;

    @InjectMocks
    BlogServiceImplementation service;

    @Nested
    public class getAllBlog{

        List<Blog> blogs = new ArrayList<>();
        Blog blogA = new Blog("titleA", Instant.now(), "descriptionA", 1L);
        Blog blogB = new Blog("titleB", Instant.now(), "descriptionB", 2L);

        @Test
        void itShouldGetAllBlogHappyCase() {
            //given
            blogs.add(blogA);
            blogs.add(blogB);
            given(blogRepository.findAll()).willReturn(blogs);

            //when
            service.getAllBlog();

            //then
            then(blogRepository).should().findAll();
            assertEquals(2, blogs.size());
        }
    }


    @Nested
    public class getBlogById{

        Long idTestHappyCase = 1L;
        Long idTestZero = 0L;
        Long idTestNull = null;
        Blog blogA = new Blog("titleA", Instant.now(), "descriptionA", 1L);
        Blog blogWithEmptyName = new Blog("", Instant.now(), "descriptionA", 1L);
        Blog blogWithNullName = new Blog(null, Instant.now(), "descriptionA", 1L);

        @Test
        void itShouldGetBlogByIdHappyCase() {
            //given
            given(blogRepository.findById(anyLong())).willReturn(Optional.of(blogA));

            //when
            Optional<Blog> blogById = service.getBlogById(idTestHappyCase);

            //then
            then(blogRepository).should().findById(anyLong());
            assertEquals("titleA", blogById.get().getName());
        }

        @Test
        @Disabled
        void itShouldGetBlogByIdWhenIdZeroWithException() {
            //given

            //when
            service.getBlogById(idTestZero);

            //then


        }

        @Test
        @Disabled
        void itShouldGetBlogByIdWhenIdNullWithException() {
            //given

            //when

            //then

        }
    }

    /*

    @Nested
    public class saveBlog{
        @Test
        void saveBlogHappyCase() {
            //given

            //when

            //then

        }
    }

    @Nested
    public class deleteBlogById{
        @Test
        void deleteBlogByIdHappyCase() {
            //given

            //when

            //then

        }
    }


     */
}