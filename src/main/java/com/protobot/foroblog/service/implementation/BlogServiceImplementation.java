package com.protobot.foroblog.service.implementation;

import com.protobot.foroblog.model.Blog;
import com.protobot.foroblog.service.BlogService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImplementation implements BlogService {
    @Override
    public List<Blog> getAllBlog() {
        return null;
    }

    @Override
    public Optional<Blog> getBlogById(Long id) {
        return Optional.empty();
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return null;
    }

    @Override
    public String deleteBlogById(Long id) {
        return null;
    }
}
