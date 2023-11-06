package com.project.treeshop.reponses;

import com.project.treeshop.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductListResponse {
    private List<Product> products;
    private int totalPages;
    private long totalProducts;
}
