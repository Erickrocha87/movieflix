package com.movieflix.controllers;

import com.movieflix.entity.Category;
import com.movieflix.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;

    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable long id){
        return categoryService.findById(id);
    }

    @PostMapping("/save")
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @PutMapping("/update/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category){
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
