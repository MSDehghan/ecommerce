package com.bestretail.ecommerce.app.cart;

import com.bestretail.ecommerce.app.product.Product;

import java.util.Objects;

public class CartItemProjection {
    private Product product;
    private Integer count;

    public CartItemProjection(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemProjection that = (CartItemProjection) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, count);
    }

    public Product getProduct() {
        return product;
    }

    public Integer getCount() {
        return count;
    }
}
