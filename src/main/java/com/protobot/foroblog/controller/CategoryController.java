package com.protobot.foroblog.controller;

import com.protobot.foroblog.dto.response.RestResponse;
import com.protobot.foroblog.exceptions.controller.category.CategoryNotZeroIdException;
import com.protobot.foroblog.exceptions.controller.category.CategoryNullStringException;
import com.protobot.foroblog.helper.CheckIfNullOrEmptyString;
import com.protobot.foroblog.model.Category;
import com.protobot.foroblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    private final CheckIfNullOrEmptyString checkIfNullOrEmptyString;

    @Autowired
    public CategoryController(CategoryService categoryService, CheckIfNullOrEmptyString checkIfNullOrEmptyString) {
        this.categoryService = categoryService;
        this.checkIfNullOrEmptyString = checkIfNullOrEmptyString;
    }

    @GetMapping
    public RestResponse<List<Category>> getAllCategories (){
        List<Category> allCategories = categoryService.getAllCategories();
        return new RestResponse<>(HttpStatus.OK, allCategories);
    }

    @GetMapping("/{id}")
    public RestResponse<Optional<Category>> getCategoryById(@PathVariable Long id){
        if(id == 0)
            throw new CategoryNotZeroIdException();
        Optional<Category> category = categoryService.getCategoryById(id);
        return new RestResponse<>(HttpStatus.OK, category);
    }

    @PostMapping
    public RestResponse<Category> saveCategory (@RequestBody Category category){
        if(!checkIfNullOrEmptyString.check(category.getName()))
            throw new CategoryNullStringException();
        Category categoryResponse = categoryService.saveCategory(category.getName());
        return new RestResponse<>(HttpStatus.CREATED, categoryResponse);
    }



}
