package com.project.treeshop.repositories;

import com.project.treeshop.dto.CategoryDTO;
import com.project.treeshop.models.Category;
import com.project.treeshop.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

//    Find product by category
    Page<Product> findByCategory(Category category, Pageable pageable);

//    Search by name product
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:name%")
    Page<Product> findProductByName(@Param("name") String name,Pageable pageable);


}
