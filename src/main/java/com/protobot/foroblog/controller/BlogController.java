package com.protobot.foroblog.controller;

import com.protobot.foroblog.dto.response.RestResponse;
import com.protobot.foroblog.model.Blog;
import com.protobot.foroblog.model.Category;
import com.protobot.foroblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/getall")
    public RestResponse<List<Blog>> getAllBlogs() {
        List<Blog> allBlog = blogService.getAllBlog();
        return new RestResponse<>(HttpStatus.ACCEPTED, allBlog);
    }

    @GetMapping("/get/{id}")
    public RestResponse<Optional<Blog>> getBlogById(@PathVariable Long id) {
        Optional<Blog> blogById = blogService.getBlogById(id);
        return new RestResponse<>(HttpStatus.ACCEPTED, blogById);
    }

    @PostMapping("/save")
    public RestResponse<Blog> saveBlog(@RequestBody Blog blog) {
        Blog blogSaved = blogService.saveBlog(blog);
        return new RestResponse<>(HttpStatus.CREATED, blogSaved);
    }

    @DeleteMapping("/delete/{id}")
    public RestResponse<String> deleteBlogById(@PathVariable Long id) {
        String stringResponse = blogService.deleteBlogById(id);
        return new RestResponse<>(HttpStatus.GONE, stringResponse);
    }
}
