package com.example.productservice.controllers;

import com.example.productservice.models.Product;
import com.example.productservice.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;
    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    /**
     * Endpoint to search for products by keyword.
     * Example: /search?keyword="example"
     *
     * @param keyword the search keyword
     * @return a list of matching products
     */
    @GetMapping
    public List<Product> search(@RequestParam("keyword") String keyword,
                                @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize)
    {
        return searchService.search(keyword, pageNumber, pageSize);
    }


}
