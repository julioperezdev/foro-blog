package com.protobot.foroblog.controller;

import com.protobot.foroblog.model.Blog;
import com.protobot.foroblog.service.BlogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(MockitoExtension.class)
class BlogControllerTest {

    @Mock
    BlogService blogService;

    @InjectMocks
    BlogController controller;

    MockMvc mockMvc;

    @Nested
    public class itShouldGetAllBlog{

        List<Blog> blogs = new ArrayList<>();
        Blog blogA = new Blog("first title", Instant.now(), "first description");
        Blog blogB = new Blog("second title", Instant.now(), "second description");
        Blog blogC = new Blog("third title", Instant.now(), "third description");

        @BeforeEach
        void setUp() {
            blogs.add(blogA);
            blogs.add(blogB);
            blogs.add(blogC);

            mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        }

        @Test
        void itShouldGetAllBlog() {
            //given
            given(blogService.getAllBlog()).willReturn(blogs);

            //when
            controller.getAllBlogs();

            //then
            then(blogService).should().getAllBlog();
            assertEquals(3, blogs.size());
        }

        @Test
        void itShouldGetAllBlogWithHttpResponseOK() throws Exception {
            //given
            given(blogService.getAllBlog()).willReturn(blogs);
//
//            //when
//            controller.getAllBlogs();

            //then
            mockMvc.perform(get("/api/v1/blog"))
                    //.andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    //.andExpect(jsonPath("$.status", is("OK")))
                    .andExpect(jsonPath("$.body", hasSize(3)));
        }



    }
}