package com.project.treeshop.services;


import com.project.treeshop.models.Category;
import com.project.treeshop.models.Product;
import com.project.treeshop.repositories.CategoryRepository;
import com.project.treeshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public Product createProduct(Product product) {

        Category category = categoryRepository.findById(product.getCategory().getId())
                     .orElse(null);
        if (category == null) {
            throw new RuntimeException("Can not find category id");
        }

//                Product newProduct = Product.builder()
//                .name(productDTO.getName())
//                .price(productDTO.getPrice())
//                .url(productDTO.getUrl())
//                .description(productDTO.getDescription())
//                .category(categoryOptional)
//                .build();

        return productRepository.save(product);
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cannot find Product id"));
    }

//    Search by Name
    @Override
    public Page<Product> findProductByName(String name, Pageable pageable) {
        return productRepository.findProductByName(name.toLowerCase(),pageable);
    }

    @Override
    public Page<Product> findByCategory(Category category, Pageable pageable) {
        return productRepository.findByCategory(category,pageable);
    }

    @Override
    public Page<Product> getAllProduct(Pageable pageable) {

        return productRepository.findAll(pageable);
    }

    @Override
    public Product updateProductById(long id, Product newProduct) {
        Product updateProduct = getProductById(id);
        updateProduct.setName(newProduct.getName() );
        updateProduct.setPrice(newProduct.getPrice());
        updateProduct.setUrl(newProduct.getUrl());
        updateProduct.setDescription(newProduct.getDescription());

        productRepository.save(updateProduct);
        return updateProduct;
    }
    @Override
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }
}
