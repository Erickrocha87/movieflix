package com.movieflix.services;

import com.movieflix.dto.CategoryRequestDTO;
import com.movieflix.dto.CategoryResponseDTO;
import com.movieflix.entity.Category;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO){
        Category category = CategoryMapper.map(categoryRequestDTO);
        categoryRepository.save(category);
        return CategoryMapper.map(category);
    }

    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }

    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO category){
        Optional<Category> verifyCategory = categoryRepository.findById(id);
        Category updatedCategory = new Category();
        if (verifyCategory.isPresent()){
            updatedCategory.setId(id);
            updatedCategory.setName(category.name());
            categoryRepository.save(updatedCategory);
            return CategoryMapper.map(updatedCategory);
        }
        return null;
    }

    public List<CategoryResponseDTO> findAll() {
        List<Category> listCategory = categoryRepository.findAll();
        return listCategory
                .stream()
                .map(CategoryMapper::map)
                .collect(Collectors.toList());
    }
}
