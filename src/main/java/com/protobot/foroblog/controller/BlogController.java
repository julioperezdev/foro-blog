package com.protobot.foroblog.controller;

import com.protobot.foroblog.dto.response.RestResponse;
import com.protobot.foroblog.model.Blog;
import com.protobot.foroblog.model.Category;
import com.protobot.foroblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping()
    public RestResponse<List<Blog>> getAllBlogs() {
        List<Blog> allBlog = blogService.getAllBlog();
        return new RestResponse<>(HttpStatus.ACCEPTED, allBlog);
    }
}
