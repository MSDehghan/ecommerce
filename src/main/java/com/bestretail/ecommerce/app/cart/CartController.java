package com.bestretail.ecommerce.app.cart;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    @PostMapping("/addItem")
    public List<CartItem> addToCart(@Valid @RequestBody addToCartItem item) {
        return new ArrayList<>();
    }
}
