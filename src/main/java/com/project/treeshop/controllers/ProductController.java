package com.project.treeshop.controllers;

import com.project.treeshop.dto.ProductDTO;
import com.project.treeshop.models.Category;
import com.project.treeshop.models.Product;
import com.project.treeshop.reponses.ProductByNameResponse;
import com.project.treeshop.reponses.ProductListResponse;
import com.project.treeshop.services.CategoryService;
import com.project.treeshop.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    @PostMapping("")
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, BindingResult result) {
        if (result.hasErrors()) {
            List<String> erroMassege = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(erroMassege);
        }
        Product newProduct = productService.createProduct(product);
        return ResponseEntity.ok(newProduct);
    }

    @GetMapping("/list") //http://localhost:8080/api/v1/products/list?page=0&size=5
    public ResponseEntity<ProductListResponse> getAllProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size)  {
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdAt").descending());

        Page<Product> allProduct = productService.getAllProduct(pageable);
        int totalPage = allProduct.getTotalPages();
        long totalProduct = allProduct.getTotalElements();
        List<Product> productResponses = allProduct.getContent();
        return ResponseEntity.ok(ProductListResponse
                .builder()
                        .products(productResponses)
                         .totalProducts(totalProduct)
                        .totalPages(totalPage)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById( @PathVariable long id) {
        try {
            Product checkProduct = productService.getProductById(id);
            return ResponseEntity.ok(checkProduct);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/findCategory/{id}")
    public ResponseEntity<Page<Product>> getProductsByCategory(@PathVariable long id, Pageable pageable) {
        Category category = categoryService.getCategoryById(id);
        Page<Product> products = productService.findByCategory(category, pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search-name/{name}")
    public ResponseEntity<List<ProductByNameResponse>> findProductByName(@PathVariable String name, Pageable pageable) {
        Page<Product> products = productService.findProductByName(name, pageable);
        List<ProductByNameResponse> responseList = new ArrayList<>();

        for (Product product : products) {
            ProductByNameResponse response = new ProductByNameResponse(product.getId(), product.getName());
            responseList.add(response);
        }

        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProductById(@PathVariable long id,
                                                    @RequestBody Product newProduct) {
        try {
            Product product = productService.updateProductById(id,newProduct);
            return ResponseEntity.ok("update successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable long id) {
           productService.deleteProductById(id);
        return ResponseEntity.ok("Delete product successfully.");
    }
}
