package com.protobot.foroblog.service.implementation;

import com.protobot.foroblog.exceptions.service.category.CategoryNullStringException;
import com.protobot.foroblog.model.Category;
import com.protobot.foroblog.repository.CategoryRepository;
import com.protobot.foroblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImplementation(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category saveCategory(String name) {
        return categoryRepository.saveCategory(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        List<Category> allCategories = categoryRepository.findAll();

        Optional<Category> categoriesFiltered = allCategories.stream()
                .filter(this::checkThatIsNotNull)
                .findFirst();
        return categoriesFiltered;

    }

    public boolean checkThatIsNotNull (Category category){
        return category.getName() != null;
    }

    @Transactional
    @Override
    public void deleteCategoryById(Long id) {
        List<Category> allCategories = categoryRepository.findAll();

        Optional<Category> categoriesFiltered = allCategories.stream()
                .findFirst();

    }

    @Transactional
    void deleteFirstCategory (Category category){
        categoryRepository.deleteById(category.getId());
    }

}
