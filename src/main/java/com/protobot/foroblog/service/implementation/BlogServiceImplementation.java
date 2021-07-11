package com.protobot.foroblog.service.implementation;

import com.protobot.foroblog.helper.CheckIfNullOrEmptyString;
import com.protobot.foroblog.helper.CheckIfNullOrZeroLong;
import com.protobot.foroblog.helper.ConvertStringToLowerCaseHelper;
import com.protobot.foroblog.model.Blog;
import com.protobot.foroblog.repository.BlogRepository;
import com.protobot.foroblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImplementation implements BlogService {

    private final BlogRepository blogRepository;

    private final ConvertStringToLowerCaseHelper convertStringToLowerCaseHelper;

    private final CheckIfNullOrEmptyString checkIfNullOrEmptyString;

    private final CheckIfNullOrZeroLong checkIfNullOrZeroLong;

    @Autowired
    public BlogServiceImplementation(BlogRepository blogRepository, ConvertStringToLowerCaseHelper convertStringToLowerCaseHelper, CheckIfNullOrEmptyString checkIfNullOrEmptyString, CheckIfNullOrZeroLong checkIfNullOrZeroLong) {
        this.blogRepository = blogRepository;
        this.convertStringToLowerCaseHelper = convertStringToLowerCaseHelper;
        this.checkIfNullOrEmptyString = checkIfNullOrEmptyString;
        this.checkIfNullOrZeroLong = checkIfNullOrZeroLong;
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogRepository.getAllBlogs();
    }

    @Override
    public Optional<Blog> getBlogById(Long id) {
        this.checkIfNullOrZeroLong.check(id);
        return blogRepository.findById(id);
    }

    @Override
    public Blog saveBlog(Blog blog) {
        this.checkIfNullOrEmptyString.check(blog.getName());
        this.checkIfNullOrEmptyString.check(blog.getDescription());
        return blogRepository.saveBlog(
                blog.getName(),
                Instant.now(),
                blog.getDescription(),
                blog.getIdCategory());
    }

    @Override
    public String deleteBlogById(Long id) {
        this.checkIfNullOrZeroLong.check(id);
        return blogRepository.deleteBlogById(id);
    }
}
