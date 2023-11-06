package com.project.treeshop.services;

import com.project.treeshop.dto.CategoryDTO;
import com.project.treeshop.dto.ProductDTO;
import com.project.treeshop.models.Category;
import com.project.treeshop.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IProductService {
    Product createProduct(Product product);
    Product getProductById(long id);
    Page<Product> findProductByName(String name,Pageable pageable); //search by name;
    Page<Product> getAllProduct(Pageable pageable);
    Page<Product> findByCategory(Category category, Pageable pageable);
//    Product updateProductById(long id, Product newProduct);
    Product updateProductById(long id, Product product);
    void deleteProductById(long id);
}
