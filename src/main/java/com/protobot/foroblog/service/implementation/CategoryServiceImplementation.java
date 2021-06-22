package com.protobot.foroblog.service.implementation;

import com.protobot.foroblog.model.Category;
import com.protobot.foroblog.repository.CategoryRepository;
import com.protobot.foroblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImplementation(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }


    @Override

    public Category saveCategory(String name) {
        if(name == null){
            System.out.println("null chico");
            return null;
        }
        if(!(name instanceof String)){
            System.out.println("insta");
            return null;
        }
        Category categoryCreated = categoryRepository.saveCategory(name);
        return categoryCreated;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
