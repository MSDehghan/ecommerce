package com.bestretail.ecommerce.app.cart;

import com.bestretail.ecommerce.app.product.Product;

public class CartItem {
    private Integer count;
    private Product product;

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
}
