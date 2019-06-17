package com.bestretail.ecommerce.app.cart;

import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }


    @ApiOperation("This endpoint is used to add or delete or update an item in user cart." +
            "if count == 0, item will be deleted otherwise item will be updated or created." +
            "User Cart is returned in response.")
    @PutMapping("/item")
    @PreAuthorize("hasRole('USER')")
    public List<CartItemProjection> putItemInCart(@Valid @RequestBody CartItemPutDto item) {
        service.putItem(item);
        return service.getCartOfCurrentUser();
    }

    @ApiOperation("This endpoint is used to add or delete or update many items in user cart." +
            "if count == 0, item will be deleted otherwise item will be updated or created." +
            "User Cart is returned in response.")
    @PutMapping("/items")
    @PreAuthorize("hasRole('USER')")
    public List<CartItemProjection> putAllInCart(@Valid @RequestBody List<CartItemPutDto> items) {
        service.putItems(items);
        return service.getCartOfCurrentUser();
    }

    @GetMapping("/cart")
    public List<CartItemProjection> getAll() {
        return service.getCartOfCurrentUser();
    }
}
