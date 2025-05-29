package com.ecommerce.ecommerce.controller.category;


import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.request.category.AddCategoryRequest;
import com.ecommerce.ecommerce.request.category.UpdateCategoryRequest;
import com.ecommerce.ecommerce.response.ApiResponse;
import com.ecommerce.ecommerce.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/category/")
@RequiredArgsConstructor
public class CategoryController {
    final CategoryService categoryService;


    @PostMapping("add-category")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody AddCategoryRequest addCategoryRequest) {
        try {
            Category category = categoryService.addCategory(addCategoryRequest);
            return ResponseEntity.ok(new ApiResponse("Category Added", category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Server Error", null));
        }
    }

    @GetMapping("categories")
    public ResponseEntity<ApiResponse> getCategories() {
        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("All Categories", categories));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Server Error", null));
        }
    }

    @GetMapping("category/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
        try {
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Category Found", category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Server Error", e.getMessage()));
        }
    }

    @PutMapping("update-category/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        try {
            Category category = categoryService.updateCategory(id, updateCategoryRequest);
            return ResponseEntity.ok(new ApiResponse("Category Updated", category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Server Error", e.getMessage()));
        }
    }


    @DeleteMapping("delete-category/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok(new ApiResponse("Category Deleted", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Server Error", null));
        }
    }
}
