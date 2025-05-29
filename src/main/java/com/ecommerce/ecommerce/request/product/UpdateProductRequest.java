package com.ecommerce.ecommerce.request.product;

import com.ecommerce.ecommerce.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {
        private String title;
        private String description;
        private String brand;
        private BigDecimal price;
        private Integer stockQuantity;
        private Boolean availability;
        private String thumbnailName;
        private String thumbnailType;
        private byte[] thumbnailData;
        private Long categoryId;

        Product updateProduct(){
            return Product.builder()
                    .title(this.title)
                    .description(this.description)
                    .brand(this.brand)
                    .price(this.price)
                    .stockQuantity(this.stockQuantity)
                    .availability(this.availability)
                    .thumbnailName(this.thumbnailName)
                    .thumbnailType(this.thumbnailType)
                    .thumbnailData(this.thumbnailData)
                    .build();
        }
}
