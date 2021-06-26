package com.protobot.foroblog.service;

import com.protobot.foroblog.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category saveCategory (String name);

    List<Category> getAllCategories();

    Optional<Category> getCategoryById(Long id);

    void deleteCategoryById(Long id);
}
