package com.bestretail.ecommerce.app.category;

import com.bestretail.ecommerce.exceptions.ResourceAlreadyExistsException;
import com.bestretail.ecommerce.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category addCategory(Category c) {
        if (c.getParent() != null && c.getParent().getId() == null)
            throw new IllegalArgumentException("Parent id must be present.");

        Optional<Category> currentCat = repository.findByName(c.getName());
        if (currentCat.isPresent())
            throw new ResourceAlreadyExistsException("Category already exists");

        return repository.save(c);
    }

    public Category findCategory(int id) {
        Optional<Category> category = repository.findById(id);
        return category.orElseThrow(IllegalArgumentException::new);
    }

    public List<Category> findAll() {
        List<Category> all = repository.findAll();
//        all.forEach(category -> category.getParent().setParent(null));
        return all;
    }

    public void removeCategory(int id) {
        Optional<Category> optionalCategory = repository.findById(id);
        if (optionalCategory.isPresent()) {
            repository.delete(optionalCategory.get());
        } else {
            throw new ResourceNotFoundException("Category not found!");
        }
    }
}
