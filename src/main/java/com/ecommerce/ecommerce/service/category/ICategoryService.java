package com.ecommerce.ecommerce.service.category;

import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.request.category.AddCategoryRequest;
import com.ecommerce.ecommerce.request.category.UpdateCategoryRequest;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
    Category addCategory(AddCategoryRequest addCategoryRequest);
    Category updateCategory(Long id, UpdateCategoryRequest updateCategoryRequest);
    void deleteCategory(Long id);
    Category getCategoryByName(String name);
}
