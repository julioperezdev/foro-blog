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
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(MockitoExtension.class)
class BlogControllerTest {

    @Mock
    BlogService blogService;

    @InjectMocks
    BlogController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Nested
    public class itShouldGetAllBlogCase{

        List<Blog> blogs = new ArrayList<>();
        Blog blogA = new Blog("first title", Instant.now(), "first description", 1L);
        Blog blogB = new Blog("second title", Instant.now(), "second description", 2L);
        Blog blogC = new Blog("third title", Instant.now(), "third description", 3L);

        @BeforeEach
        void setUp() {
            blogs.add(blogA);
            blogs.add(blogB);
            blogs.add(blogC);
        }

        @Test
        void itShouldGetAllBlogHappyCase() throws Exception {
            //given
            given(blogService.getAllBlog()).willReturn(blogs);

            //when
            controller.getAllBlogs();

            //then
            then(blogService).should().getAllBlog();
            assertEquals(3, blogs.size());
            mockMvc.perform(get("/api/v1/blog"))
                    //.andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    //.andExpect(jsonPath("$.status", is("OK")))
                    .andExpect(jsonPath("$.body", hasSize(3)));
        }
    }

    @Nested
    public class itShouldGetBlogByIdCase{

        Blog blog = new Blog(1L,"title", Instant.now(), "description");


        @Test
        void itShouldGetBlogByIdHappyCase() throws Exception {
            //given
            Long id = 1L;
            given(blogService.getBlogById(anyLong())).willReturn(Optional.of(blog));

            //when
            controller.getBlogById(id);

            //then
            then(blogService).should().getBlogById(anyLong());
            mockMvc.perform(get("/api/v1/blog/"+blog.getId()))
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }
    }

    @Nested
    public class itShouldSaveCase{

        Blog blog = new Blog(1L,"title", Instant.now(), "description");

        @Test
        void itShouldSaveBlogHappyCase() throws Exception {
            //given
            given(blogService.saveBlog(any(Blog.class))).willReturn(blog);

            //when
            controller.saveBlog(blog);

            //then
            then(blogService).should().saveBlog(any(Blog.class));
            mockMvc.perform(post("/api/v1/blog/"))
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }
    }

    @Nested
    public class itShouldDeleteByIdCase{

        @Test
        void itShouldDeleteByIdHappyCase() throws Exception {
            //given
            Long id = 1L;
            String responseService = "OK";
            given(blogService.deleteBlogById(anyLong())).willReturn(responseService);

            //when
            controller.deleteBlogById(id);

            //then
            then(blogService).should().deleteBlogById(id);
            mockMvc.perform(delete("/api/v1/blog/"+id))
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }
    }
}