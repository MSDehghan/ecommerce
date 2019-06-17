package com.bestretail.ecommerce.app.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, CartItem.CartItemId> {
    @Transactional(readOnly = true)
    @Query("SELECT c FROM CartItem c WHERE c.user.id = :userId AND c.product.pid = :productId")
    Optional<CartItem> findCartItem(@Param("productId") int productId, @Param("userId") int userId);

    List<CartItemProjection> findAllByUser_Id(Integer userId);
}
