package com.bestretail.ecommerce.app.promo;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/promo")
public class PromoController {
    private final PromoService service;

    public PromoController(PromoService service) {
        this.service = service;
    }

    @PostMapping
    public Promo addPromo(@Valid @RequestBody Promo promo) {
        return service.addPromo(promo);
    }

    @GetMapping("/{id}")
    public Promo getCategory(@PathVariable int id) {
        return service.findPromoById(id);
    }

    @GetMapping("/all")
    public List<Promo> getAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.removePromo(id);
    }
}
