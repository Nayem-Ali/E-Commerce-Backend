package com.ecommerce.ecommerce.request.category;

import com.ecommerce.ecommerce.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryRequest {
    private Long id;
    private String name;
    private String description;

    public Category createCategory(){
        return Category.builder()
                .name(this.name)
                .description(this.description)
                .build();
    }
}
