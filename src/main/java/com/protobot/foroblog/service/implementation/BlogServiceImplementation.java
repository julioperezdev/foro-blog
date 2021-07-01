package com.protobot.foroblog.service.implementation;

import com.protobot.foroblog.model.Blog;
import com.protobot.foroblog.service.BlogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImplementation implements BlogService {
    @Override
    public List<Blog> getAllBlog() {
        return null;
    }
}
