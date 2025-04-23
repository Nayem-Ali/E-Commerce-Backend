package com.ecommerce.ecommerce.service.product;

import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.request.product.AddProductRequest;
import com.ecommerce.ecommerce.request.product.UpdateProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);

    List<Product> gettAllProducts();

    Product addProduct(AddProductRequest addProductRequest, MultipartFile imageFile);

    Product updateProduct(Long id, UpdateProductRequest updateProductRequest, MultipartFile imageFile);

    void deleteProduct(Long id);

    List<Product> getProductByTitle(String title);

    List<Product> getProductByCategory(Long categoryId);
}
