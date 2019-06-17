package com.bestretail.ecommerce.app.cart;

import com.bestretail.ecommerce.app.product.Product;
import com.bestretail.ecommerce.app.user.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(CartItem.CartItemId.class)
public class CartItem {
    @Positive
    private Integer count;

    @Id
    @NotNull
    @ManyToOne(optional = false)
    private Product product;

    @Id
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public static class CartItemId implements Serializable {
        @NotNull
        private Product product;

        @NotNull
        private UserEntity user;

        public CartItemId(@NotNull Product product, @NotNull UserEntity user) {
            this.product = product;
            this.user = user;
        }

        private CartItemId() {

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CartItemId that = (CartItemId) o;
            return product.equals(that.product) &&
                    user.equals(that.user);
        }

        @Override
        public int hashCode() {
            return Objects.hash(product, user);
        }
    }
}
