package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class FakeStoreServiceImpl implements ProductService {
    RestTemplate restTemplate;
    //RedisTemplate<String, Object> redisTemplate;
    public FakeStoreServiceImpl(RestTemplate restTemplate)
    //RedisTemplate<String, Object> redisTemplate)
    {
        this.restTemplate = restTemplate;
        //this.redisTemplate = redisTemplate;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
//        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS","PRODUCT_" + id);
//        if(product != null) {
//            return product;
//        }
        FakeStoreProductDto fakeStoreProductDto= restTemplate
                .getForObject("https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDto.class);
        if(fakeStoreProductDto == null) {
            throw new ProductNotFoundException(100L, "Product not found for id:" + id);
        }
        Product product= convertFakeSToreProductDtoToProduct(fakeStoreProductDto);
        //redisTemplate.opsForHash().put("PRODUCTS","PRODUCT_" + id, product);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtoList = restTemplate
                .getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        if(fakeStoreProductDtoList != null) {
            List<Product> productList = new ArrayList<>();
            for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtoList) {
                productList.add(convertFakeSToreProductDtoToProduct(fakeStoreProductDto));
            }
            return productList;
        }
        return null;
    }
    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        //fakeStoreProductDto.setId(id);
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        //fakeStoreProductDto.setCategory(product.getCategory().getDescription());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor
                = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto1 = restTemplate.execute(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.PUT, requestCallback, responseExtractor)
                .getBody();
        return convertFakeSToreProductDtoToProduct(fakeStoreProductDto1);
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getDescription());

        FakeStoreProductDto createdFakeStoreProductDto = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreProductDto,
                FakeStoreProductDto.class);
        return convertFakeSToreProductDtoToProduct(createdFakeStoreProductDto);
    }


    public Product convertFakeSToreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        if (fakeStoreProductDto == null) {
            return null;
        }
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
}
