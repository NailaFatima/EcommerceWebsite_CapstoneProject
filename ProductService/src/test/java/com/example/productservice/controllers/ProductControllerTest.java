package com.example.productservice.controllers;

import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @Autowired
    private TestRestTemplate restTemplate;

   // @Qualifier("SelfProductServiceImpl")
    @MockBean(name = "SelfProductServiceImpl")
    private ProductService productService;

    private Product testProduct;

    @BeforeEach
    void setup() {
        // Arrange: Prepare test data
        testProduct = new Product();
        testProduct.setDescription("Test Product");
        testProduct.setPrice(99.99);

        // Mock the productService.createProduct method
        when(productService.createProduct(any(Product.class))).thenReturn(testProduct);

        //testProduct = productService.createProduct(testProduct);
        testProduct = productService.createProduct(testProduct);
    }

    @Test
    void testGetProductById() {
        // Act: Perform the GET request
        ResponseEntity<Product> response = restTemplate.getForEntity("/products/" + testProduct.getId(), Product.class);

        // Assert: Validate response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Product", response.getBody().getDescription());
    }

    @Test
    void testGetAllProducts() {
        // Act
        ResponseEntity<Product[]> response = restTemplate.getForEntity("/products", Product[].class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    void testCreateProduct() {
        // Arrange
        Product newProduct = new Product();
        newProduct.setDescription("New Product");
        newProduct.setPrice(49.99);
        HttpEntity<Product> request = new HttpEntity<>(newProduct);

        // Act
        ResponseEntity<Product> response = restTemplate.postForEntity("/products", request, Product.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getId());
    }

    @Test
    void testReplaceProduct() {
        // Arrange
        testProduct.setDescription("Updated Product");
        HttpEntity<Product> request = new HttpEntity<>(testProduct);

        // Act
        ResponseEntity<Product> response = restTemplate.exchange("/products/" + testProduct.getId(), HttpMethod.PUT, request, Product.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Product", response.getBody().getDescription());
    }
}
