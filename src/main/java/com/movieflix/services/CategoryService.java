package com.movieflix.services;

import com.movieflix.entity.Category;
import com.movieflix.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {


    private final CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        return category;
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }

    public Category updateCategory(Long id, Category category){

        Optional<Category> verifyCategory = categoryRepository.findById(id);
        Category updatedCategory = new Category();
        if (verifyCategory.isPresent()){
            updatedCategory.setId(id);
            updatedCategory.setName(category.getName());
            categoryRepository.save(updatedCategory);
            return updatedCategory;
        }
        return null;
    }
}
