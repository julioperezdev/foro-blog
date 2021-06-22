package com.protobot.foroblog.controller;

import com.protobot.foroblog.dto.response.RestResponse;
import com.protobot.foroblog.model.Category;
import com.protobot.foroblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public RestResponse<List<Category>> getAllCategories (){
        List<Category> allCategories = categoryService.getAllCategories();
        return new RestResponse<>(HttpStatus.OK, allCategories);
    }

    @PostMapping
    public RestResponse<Category> saveCategory (@RequestBody Category category){
        Category categoryResponse = categoryService.saveCategory(category.getName());
        if(categoryResponse == null){
            return new RestResponse<>(HttpStatus.BAD_REQUEST, null);
        }
        return new RestResponse<>(HttpStatus.CREATED, categoryResponse);
    }
}
