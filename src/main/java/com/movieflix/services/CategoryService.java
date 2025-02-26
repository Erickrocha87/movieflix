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

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryResponseDTO findById(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        return category
                .map(categoryMapper::map)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO){
        Category category = categoryMapper.map(categoryRequestDTO);
        categoryRepository.save(category);
        return categoryMapper.map(category);
    }

    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }

    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO category){
        Optional<Category> verifyCategory = categoryRepository.findById(id);
        Category updatedCategory = new Category();
        if (verifyCategory.isPresent()){
            updatedCategory.setId(id);
            updatedCategory = categoryMapper.map(category);
            categoryRepository.save(updatedCategory);
            return categoryMapper.map(updatedCategory);
        }
        return null;
    }

    public List<CategoryResponseDTO> findAll() {
        List<Category> listCategory = categoryRepository.findAll();
        return listCategory
                .stream()
                .map(categoryMapper::map)
                .collect(Collectors.toList());
    }
}
