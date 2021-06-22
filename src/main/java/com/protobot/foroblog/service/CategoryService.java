package com.protobot.foroblog.service;

import com.protobot.foroblog.model.Category;

import java.util.List;

public interface CategoryService {

    Category saveCategory (String name);

    List<Category> getAllCategories();
}
