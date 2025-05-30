package com.ecommerce.ecommerce.service.product;

import com.ecommerce.ecommerce.EcommerceApplication;
import com.ecommerce.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.repository.product.ProductRepository;
import com.ecommerce.ecommerce.request.product.AddProductRequest;
import com.ecommerce.ecommerce.request.product.UpdateProductRequest;
import com.ecommerce.ecommerce.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    final ProductRepository productRepository;
    final CategoryService categoryService;

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not Found"));
    }

    @Override
    public List<Product> gettAllProducts() {
        return productRepository.findAll().stream().toList();
    }

    @Override
    public Product addProduct(AddProductRequest addProductRequest, MultipartFile imageFile) {
        try {
            Product product = addProductRequest.createProduct();
            if (addProductRequest.getCategoryId() != null) {
                Category category = categoryService.getCategoryById(addProductRequest.getCategoryId());
                product.setCategory(category);
            }
            product.setThumbnailName(imageFile.getName());
            product.setThumbnailType(imageFile.getContentType());
            product.setThumbnailData(imageFile.getBytes());
            return productRepository.save(product);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product updateProduct(Long id, UpdateProductRequest updateProductRequest, MultipartFile imageFile) {
        Product product = getProductById(id);
        try {
            if (updateProductRequest.getTitle() != null) {
                product.setTitle(updateProductRequest.getTitle());
            }
            if (updateProductRequest.getDescription() != null) {
                product.setDescription(updateProductRequest.getDescription());
            }
            if (updateProductRequest.getBrand() != null) {
                product.setBrand(updateProductRequest.getBrand());
            }
            if (updateProductRequest.getPrice() != null) {
                product.setPrice(updateProductRequest.getPrice());
            }
            if (updateProductRequest.getAvailability() != null) {
                product.setAvailability(updateProductRequest.getAvailability());
            }
            if (updateProductRequest.getStockQuantity() != null) {
                product.setStockQuantity(updateProductRequest.getStockQuantity());
            }
            ///  Update Image
            if (!imageFile.isEmpty()) {
                product.setThumbnailName(imageFile.getOriginalFilename());
                product.setThumbnailType(imageFile.getContentType());
                product.setThumbnailData(imageFile.getBytes());
            }
            /// Update Category
            if(updateProductRequest.getCategoryId() != null){
                Category category = categoryService.getCategoryById(updateProductRequest.getCategoryId());
                product.setCategory(category);
            }
            return  productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    @Override
    public List<Product> getProductByTitle(String title) {
        return productRepository.findAllByTitle(title).stream().toList();
    }

    @Override
    public List<Product> getProductByCategory(Long categoryId) {
        return productRepository.getAllTodosByCategory(categoryId).stream().toList();
    }
}
