package com.example.productservice.controllers;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import com.example.productservice.services.TokenService;
import org.hibernate.cache.spi.access.UnknownAccessTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
     ProductService productService;
     TokenService tokenService;
    @Autowired
    public ProductController(@Qualifier("SelfProductServiceImpl") ProductService productService, TokenService tokenService) {
        this.productService = productService;
        this.tokenService = tokenService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById (
            //@RequestHeader("Token") String token,
            @PathVariable("id") Long id)
            throws ProductNotFoundException {
//         if(!tokenService.validateToken(token)){
//            throw new UnknownAccessTypeException("User is not authorised");
//         }
        Product product = null;
        product= productService.getProductById(id);
        ResponseEntity<Product> productResponseEntity;
        productResponseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return productResponseEntity;
    }
    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct= productService.createProduct(product);
        return ResponseEntity.ok(savedProduct);
    }
}
