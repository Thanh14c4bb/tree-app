package com.project.treeshop.services;

import com.project.treeshop.dto.CategoryDTO;
import com.project.treeshop.models.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Category createCategory(CategoryDTO category);
    Category getCategoryById(long id);
    List<Category> getAllCategories();
    Category updateCategory(long id, CategoryDTO category);
    void deleteCategory(long id);
}
