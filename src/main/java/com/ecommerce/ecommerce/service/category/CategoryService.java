package com.ecommerce.ecommerce.service.category;

import com.ecommerce.ecommerce.exception.CategoryNotFoundException;
import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.repository.category.CategoryRepository;
import com.ecommerce.ecommerce.request.category.AddCategoryRequest;
import com.ecommerce.ecommerce.request.category.UpdateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    final CategoryRepository categoryRepository;
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not Found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll().stream().toList();
    }

    @Override
    public Category addCategory(AddCategoryRequest addCategoryRequest) {
        Category category = addCategoryRequest.createCategory();
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, UpdateCategoryRequest updateCategoryRequest) {
        Category category = getCategoryById(id);
        // TODO: Check values and then update them accordingly
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }
}
