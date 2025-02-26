package com.movieflix.mapper;

import com.movieflix.dto.CategoryRequestDTO;
import com.movieflix.dto.CategoryResponseDTO;
import com.movieflix.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category map(CategoryRequestDTO categoryRequestDTO){
        return Category
                .builder()
                .id(categoryRequestDTO.id())
                .name(categoryRequestDTO.name())
                .build();
    }

    public CategoryResponseDTO map(Category category){
        return CategoryResponseDTO
                .builder()
                .name(category.getName())
                .build();
    }
}
