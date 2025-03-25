package com.example.productservice.services;

import com.example.productservice.models.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchService {
    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
//SEARCH
//    public List<Product> search(String keyword) {
//        return productRepository.findByTitleContains(keyword);
//    }

    //SEARCH WITH PAGINATION
//public List<Product> search(String keyword, int pageNumber, int pageSize) {
//    return productRepository.findByTitleContains(keyword, PageRequest.of(pageNumber, pageSize));
//}

    //SEARCH WITH PAGINATION AND SORTING
public List<Product> search(String keyword, int pageNumber, int pageSize) {
    return productRepository.findByTitleContains(keyword, PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "price")));
}

}
