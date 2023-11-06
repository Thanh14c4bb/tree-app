package com.project.treeshop.configuration;

import com.project.treeshop.dto.ProductDTO;
import com.project.treeshop.models.Category;
import com.project.treeshop.models.Product;
import com.project.treeshop.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {


    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }
}

