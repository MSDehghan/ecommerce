package com.bestretail.ecommerce.app.product;

import com.bestretail.ecommerce.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Product> searchInName(String search) {
        return repository.findProductsByNameIsLike(search);
    }

    public List<Product> getAllInCat(int catId) {
        return repository.findProductsByCategoryId(catId);
    }
}
