package com.gyminv.gyminv.service;

import com.gyminv.gyminv.model.Category;
import com.gyminv.gyminv.model.Equipment;
import com.gyminv.gyminv.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // konstruktur ciagnie z repository
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // pobieranko calego sprzetu
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // pobieranko po id
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // zapisywanie + walidacja
    public Category saveCategory(Category category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Nazwa kategori nie moze być pusta!");
        }
        return categoryRepository.save(category);
    }

    // usuwanko
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}