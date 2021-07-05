package com.protobot.foroblog.service.implementation;

import com.protobot.foroblog.helper.CheckIfNullOrEmptyString;
import com.protobot.foroblog.helper.CheckIfNullOrZeroLong;
import com.protobot.foroblog.helper.ConvertStringToLowerCaseHelper;
import com.protobot.foroblog.model.Category;
import com.protobot.foroblog.repository.CategoryRepository;
import com.protobot.foroblog.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService{

    Logger logger = LoggerFactory.getLogger(CategoryServiceImplementation.class);

    private final CategoryRepository categoryRepository;

    private final ConvertStringToLowerCaseHelper convertStringToLowerCaseHelper;

    private final CheckIfNullOrEmptyString checkIfNullOrEmptyString;

    private final CheckIfNullOrZeroLong checkIfNullOrZeroLong;

    @Autowired
    public CategoryServiceImplementation(CategoryRepository categoryRepository, ConvertStringToLowerCaseHelper convertStringToLowerCaseHelper, CheckIfNullOrEmptyString checkIfNullOrEmptyString, CheckIfNullOrZeroLong checkIfNullOrZeroLong){
        this.categoryRepository = categoryRepository;
        this.convertStringToLowerCaseHelper = convertStringToLowerCaseHelper;
        this.checkIfNullOrEmptyString = checkIfNullOrEmptyString;
        this.checkIfNullOrZeroLong = checkIfNullOrZeroLong;
    }

    @Override
    public Category saveCategory(String name) {
        this.checkIfNullOrEmptyString.check(name);
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
        this.checkIfNullOrZeroLong.check(id);
        List<Category> allCategories = categoryRepository.findAll();
        return allCategories.stream()
                .filter(Objects::nonNull)
                .filter((i) -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean deleteCategoryById(Long id) {
        this.checkIfNullOrZeroLong.check(id);
        return false;
    }


    public boolean checkThatIsNotNull (Category category){
        return category.getName() != null;
    }

}
