package com.project.treeshop.reponses;
import com.project.treeshop.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductByNameResponse {
    private Long id;
    private String name;

    public ProductByNameResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
    }

}
