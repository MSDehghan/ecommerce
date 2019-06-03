package com.bestretail.ecommerce.app.maker;

import com.bestretail.ecommerce.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MakerService {
    private final MakerRepository repository;

    public MakerService(MakerRepository repository) {
        this.repository = repository;
    }


    public Maker addMaker(Maker maker) {
        return repository.save(maker);
    }

    public Maker getMaker(int id) {
        Optional<Maker> optionalMaker = repository.findById(id);

        if (!optionalMaker.isPresent())
            throw new ResourceNotFoundException("Maker not found!");

        return optionalMaker.get();
    }

    public List<Maker> getAll() {
        return repository.findAll();
    }
}
