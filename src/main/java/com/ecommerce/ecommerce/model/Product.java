package com.ecommerce.ecommerce.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String brand;
    private BigDecimal price;
    private Integer stockQuantity;
    private Boolean availability;
    private String thumbnailName;
    private String thumbnailType;
    @Lob
    private byte[] thumbnailData;


    @ManyToOne()
    @JoinColumn(name = "fk_category_id")
    private Category category;
}