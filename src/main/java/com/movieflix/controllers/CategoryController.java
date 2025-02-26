package com.movieflix.controllers;

import com.movieflix.dto.CategoryRequestDTO;
import com.movieflix.dto.CategoryResponseDTO;
import com.movieflix.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll(){
        List<CategoryResponseDTO> listCategory = categoryService.findAll();
        return ResponseEntity
                .ok()
                .body(listCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable long id){
        CategoryResponseDTO category = categoryService.findById(id);
        return ResponseEntity
                .ok()
                .body(category);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO categoryRequest){
        CategoryResponseDTO category = categoryService.createCategory(categoryRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO categoryRequest){
        CategoryResponseDTO category = categoryService.updateCategory(id, categoryRequest);
        return ResponseEntity
                .ok()
                .body(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
