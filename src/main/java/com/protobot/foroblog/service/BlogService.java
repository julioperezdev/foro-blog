package com.protobot.foroblog.service;

import com.protobot.foroblog.model.Blog;

import java.util.List;
import java.util.Optional;

public interface BlogService {

    List<Blog> getAllBlog();

    //Optional<Blog> getBlogById(Long id);

    //Blog saveCategory(Blog blog);


}
