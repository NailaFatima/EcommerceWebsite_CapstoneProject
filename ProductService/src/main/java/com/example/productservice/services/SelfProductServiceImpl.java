package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SelfProductServiceImpl implements ProductService{
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    public SelfProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException{
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
//        Category category = categoryRepository.findByTitle(product.getCategory().getTitle())
//                .orElseGet(() -> {
//                    Category newCategory = new Category();
//                    newCategory.setTitle(product.getCategory().getTitle());
//                    return categoryRepository.save(newCategory);
//                }
//                );
//        product.setCategory(category);

//        Category category = product.getCategory();
//        if(category.getId() == null) {
//            Category savedCategory = categoryRepository.save(category);
//            product.setCategory(savedCategory);
//        }
//        else {
//            // we should check if category is valid or not
//
//        }
        return productRepository.save(product);
    }
}
