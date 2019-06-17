package com.bestretail.ecommerce.app.cart;

import com.bestretail.ecommerce.app.product.Product;
import com.bestretail.ecommerce.app.user.UserEntity;
import com.bestretail.ecommerce.security.SecurityUtils;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class CartItemPutDto {
    @Positive
    private Integer productId;

    @PositiveOrZero
    private Integer count;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public CartItem toEntity() {
        Integer userId = SecurityUtils.getCurrentUserId().orElseThrow(() ->
                new IllegalArgumentException("User not logged in")
        );

        Product product = new Product();
        product.setPid(productId);

        UserEntity user = new UserEntity();
        user.setId(userId);

        CartItem newItem = new CartItem();
        newItem.setProduct(product);
        newItem.setUser(user);
        newItem.setCount(count);

        return newItem;
    }
}

