package com.bestretail.ecommerce.app.product;

import com.bestretail.ecommerce.app.product.dto.SearchResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/")
    public Product add(@Valid @RequestBody Product product) {
        return service.add(product);
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable("id") int id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        service.removeById(id);
    }

    @GetMapping("/search")
    public List<SearchResult> searchInName(@RequestParam String search) {
        if (search == null || search.isEmpty() || search.length() < 3)
            throw new IllegalArgumentException("Search request should be at least 3 characters.");

        return service.searchInName(search);
    }

    @GetMapping("/category")
    public List<SearchResult> getAllInCategory(int catId) {
        return service.getAllInCat(catId);
    }
}

