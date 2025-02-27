package com.movieflix.mapper;

import com.movieflix.dto.CategoryRequestDTO;
import com.movieflix.dto.CategoryResponseDTO;
import com.movieflix.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public Category map(CategoryRequestDTO categoryRequestDTO){
        return Category
                .builder()
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
