package com.ecommerce.ecommerce.repository.product;

import com.ecommerce.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByTitle(String string);
    @Query(value = "SELECT * FROM products WHERE fk_category_id = :categoryId", nativeQuery = true)
    List<Product> getAllTodosByCategory(@Param("categoryId") Long categoryId);
}
