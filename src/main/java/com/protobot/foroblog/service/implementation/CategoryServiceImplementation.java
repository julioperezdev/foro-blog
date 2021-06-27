package com.protobot.foroblog.service.implementation;

import com.protobot.foroblog.helper.ConvertStringToLowerCaseHelper;
import com.protobot.foroblog.model.Category;
import com.protobot.foroblog.repository.CategoryRepository;
import com.protobot.foroblog.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Console;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService{

    Logger logger = LoggerFactory.getLogger(CategoryServiceImplementation.class);

    private final CategoryRepository categoryRepository;

    private final ConvertStringToLowerCaseHelper convertStringToLowerCaseHelper;

    @Autowired
    public CategoryServiceImplementation(CategoryRepository categoryRepository, ConvertStringToLowerCaseHelper convertStringToLowerCaseHelper){
        this.categoryRepository = categoryRepository;
        this.convertStringToLowerCaseHelper = convertStringToLowerCaseHelper;
    }

    @Override
    public Category saveCategory(String name) {
        String namedWithLowerCase = convertStringToLowerCaseHelper.convert(name);
        logger.info(String.format("Executing Save Category with name %s", namedWithLowerCase));
        return categoryRepository.saveCategory(namedWithLowerCase);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        List<Category> allCategories = categoryRepository.findAll();
        return allCategories.stream()
                .filter(Objects::nonNull)
                .filter((i) -> i.getId().equals(id))
                .findFirst();
    }

    @Transactional
    @Override
    public void deleteCategoryById(Long id) {
        List<Category> allCategories = categoryRepository.findAll();

        Optional<Category> categoriesFiltered = allCategories.stream()
                .findFirst();
    }

    public boolean checkThatIsNotNull (Category category){
        return category.getName() != null;
    }

}
