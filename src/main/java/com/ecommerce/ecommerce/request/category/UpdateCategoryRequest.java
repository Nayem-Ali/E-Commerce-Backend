package com.ecommerce.ecommerce.request.category;

import com.ecommerce.ecommerce.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequest {
    private Long id;
    private String name;
    private String description;

    Category create(){
        return Category.builder()
                .name(this.name)
                .description(this.description)
                .build();
    }
}
