package com.bestretail.ecommerce.app.product;

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

    @GetMapping("/search")
    public List<Product> searchInName(@RequestParam String search) {
        if (search == null || search.isEmpty() || search.length() < 3)
            throw new IllegalArgumentException("Search request should be at least 3 characters.");

        return service.searchInName(search);
    }

    @GetMapping("/category")
    public List<Product> getAllInCategory(int catId) {
        return service.getAllInCat(catId);
    }
}

