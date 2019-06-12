package com.bestretail.ecommerce.app.product;

import com.bestretail.ecommerce.app.product.dto.SearchResult;
import com.bestretail.ecommerce.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product add(Product product) {
        return repository.save(product);
    }

    public Product getById(int id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (!optionalProduct.isPresent())
            throw new ResourceNotFoundException("Product Not Found");

        return optionalProduct.get();
    }

    public List<SearchResult> searchInName(String search) {
        return repository.findProductsByNameIsLike(search).stream()
                .map(product -> new SearchResult(product, new Date(), 1, 1))
                .collect(Collectors.toList());
    }

    public List<SearchResult> getAllInCat(int catId) {
        return repository.findProductsByCategoryId(catId).stream()
                .map(product -> new SearchResult(product, new Date(), 1, 1))
                .collect(Collectors.toList());
    }
}
