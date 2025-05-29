package com.ecommerce.ecommerce.controller.product;

import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.request.product.AddProductRequest;
import com.ecommerce.ecommerce.request.product.UpdateProductRequest;
import com.ecommerce.ecommerce.response.ApiResponse;
import com.ecommerce.ecommerce.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/product/")
@RequiredArgsConstructor
public class ProductController {
    final ProductService productService;

    @PostMapping("add-product")
    public ResponseEntity<ApiResponse> addProduct(@RequestPart("productDetails") AddProductRequest addProductRequest,
                                                  @RequestPart("thumbnail") MultipartFile thumbnail) {
        try {
            System.out.println("Incoming " + thumbnail.getContentType());
            Product product = productService.addProduct(addProductRequest, thumbnail);
            return ResponseEntity.ok(new ApiResponse("Product Added", product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Server Error", e.getMessage()));
        }
    }

    @GetMapping("products")
    public ResponseEntity<ApiResponse> products() {
        try {
            List<Product> products = productService.gettAllProducts();
            return ResponseEntity.ok(new ApiResponse("All Products", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Server Error", e.getMessage()));
        }
    }

    @GetMapping("product/{id}")
    public ResponseEntity<ApiResponse> product(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(new ApiResponse("Product Found", product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Server Error", e.getMessage()));
        }
    }

    @GetMapping("product-by-title/{title}")
    public ResponseEntity<ApiResponse> productByTitle(@PathVariable String title) {
        try {
            List<Product> products = productService.getProductByTitle(title);
            return ResponseEntity.ok(new ApiResponse("Search Result", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Server Error", e.getMessage()));
        }
    }

    @GetMapping("product-by-category/{id}")
    public ResponseEntity<ApiResponse> productByCategory(@PathVariable Long id) {
        try {
            List<Product> products = productService.getProductByCategory(id);
            return ResponseEntity.ok(new ApiResponse("Search Result", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Server Error", e.getMessage()));
        }
    }

    @PutMapping("update-product/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id,
                                              @RequestPart("productDetails") UpdateProductRequest updateProductRequest,
                                              @RequestPart("thumbnail") MultipartFile thumbnail) {
        try {
            Product product = productService.updateProduct(id, updateProductRequest, thumbnail);
            return ResponseEntity.ok(new ApiResponse("Product Updated", product));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Server Error", e.getMessage()));
        }
    }
    @DeleteMapping("delete-product/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok(new ApiResponse("Product Deleted", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Server Error", e.getMessage()));
        }
    }


}
