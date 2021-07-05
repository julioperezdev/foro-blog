package com.protobot.foroblog.controller;

import com.protobot.foroblog.dto.response.RestResponse;
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

    private final String MESSAGE_RESPONSE_SUCCESS_DELETED_BY_ID ="Has been removed";
    private final String MESSAGE_RESPONSE_SUCCESS_GET_CATEGORIES ="Successful search";

    private final CategoryService categoryService;


    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public RestResponse<List<Category>> getAllCategories (){
        List<Category> allCategories = categoryService.getAllCategories();
        return new RestResponse<>(HttpStatus.OK, MESSAGE_RESPONSE_SUCCESS_GET_CATEGORIES,allCategories);
    }

    @GetMapping("/{id}")
    public RestResponse<Optional<Category>> getCategoryById(@PathVariable Long id){
        Optional<Category> category = categoryService.getCategoryById(id);
        return new RestResponse<>(HttpStatus.OK, MESSAGE_RESPONSE_SUCCESS_GET_CATEGORIES,category);
    }

    @PostMapping
    public RestResponse<Category> saveCategory (@RequestBody Category category){
        Category categoryResponse = categoryService.saveCategory(category.getName());
        return new RestResponse<>(HttpStatus.CREATED, categoryResponse);
    }

    @DeleteMapping("{id}")
    public RestResponse<String> deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
        return new RestResponse<>(HttpStatus.ACCEPTED, MESSAGE_RESPONSE_SUCCESS_DELETED_BY_ID);
    }



}
