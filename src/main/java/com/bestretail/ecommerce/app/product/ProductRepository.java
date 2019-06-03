package com.bestretail.ecommerce.app.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findProductsByNameIsLike(String search);

    List<Product> findProductsByCategoryId(Integer catId);
}
