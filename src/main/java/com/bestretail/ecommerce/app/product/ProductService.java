package com.bestretail.ecommerce.app.product;

import com.bestretail.ecommerce.app.product.dto.SearchResult;
import com.bestretail.ecommerce.app.promo.Promo;
import com.bestretail.ecommerce.app.promo.PromoRepository;
import com.bestretail.ecommerce.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final PromoRepository promoRepository;

    public ProductService(ProductRepository repository, PromoRepository promoRepository) {
        this.repository = repository;
        this.promoRepository = promoRepository;
    }

    public Product add(Product product) {
        if (product.getPromotion() != null && product.getPromotion().getId() != null) {
            Optional<Promo> promoOptional = promoRepository.findById(product.getPromotion().getId());
            if (promoOptional.isPresent()) {
                product.setPromotion(promoOptional.get());
            } else {
                throw new ResourceNotFoundException("Promo Not Found!");
            }
        }
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

    public void removeById(int pid) {
        Optional<Product> optionalProduct = repository.findById(pid);
        optionalProduct.orElseThrow(() ->
                new ResourceNotFoundException("ProductNotFound")
        );
        repository.delete(optionalProduct.get());
    }

    public List<Product> getTop10() {
        return repository.findTop10By();
    }

    public List<Product> getTop10Promotions() {
        return repository.findTop10ByPromotionIsNotNull();
    }
}
