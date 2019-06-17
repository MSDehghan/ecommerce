package com.bestretail.ecommerce.app.cart;

import com.bestretail.ecommerce.security.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository repository;

    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void putItems(List<CartItemPutDto> list) {
        for (CartItemPutDto itemPutDto : list) {
            putItem(itemPutDto);
        }
    }

    @Transactional
    public void putItem(CartItemPutDto cartItem) {
        Integer userId = SecurityUtils.getCurrentUserId().orElseThrow(() ->
                new IllegalArgumentException("User not logged in")
        );
        Optional<CartItem> item = repository.findCartItem(cartItem.getProductId(), userId);
        if (item.isPresent()) {
            if (cartItem.getCount() == 0) {
                repository.delete(item.get());
            } else {
                item.get().setCount(cartItem.getCount());
            }
        } else {
            if (cartItem.getCount() > 0) {
                repository.save(cartItem.toEntity());
            }
        }
    }

    public List<CartItemProjection> getCartOfCurrentUser() {
        Integer userId = SecurityUtils.getCurrentUserId().orElseThrow(() ->
                new IllegalArgumentException("User not logged in")
        );

        return repository.findAllByUser_Id(userId);
    }
}
