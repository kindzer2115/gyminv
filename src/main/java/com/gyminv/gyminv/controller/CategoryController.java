package com.gyminv.gyminv.controller;

import com.gyminv.gyminv.model.Category;
import com.gyminv.gyminv.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //wszystko wypluwa
    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAllCategories();
    }

    //create
    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category details) {
        return categoryService.getCategoryById(id).map(category -> {
            category.setName(details.getName());
            return ResponseEntity.ok(categoryService.saveCategory(category));
        }).orElse(ResponseEntity.notFound().build());
    }

    //usuwanko
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}