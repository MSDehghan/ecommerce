package com.bestretail.ecommerce.app.promo;

import com.bestretail.ecommerce.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromoService {

    private final PromoRepository repository;

    public PromoService(PromoRepository repository) {
        this.repository = repository;
    }

    public Promo addPromo(Promo promo) {
        return repository.save(promo);
    }

    public Promo findPromoById(int id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Promo not found!"));
    }

    public List<Promo> findAll() {
        return repository.findAll();
    }

    public void removePromo(int id) {
        Promo promo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Promo not found!"));
        repository.delete(promo);
    }
}
