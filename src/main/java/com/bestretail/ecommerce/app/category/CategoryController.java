package com.bestretail.ecommerce.app.category;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/category", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public Category addCategory(@Valid @RequestBody Category category) {
        return service.addCategory(category);
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable int id) {
        return service.findCategory(id);
    }

    @GetMapping("/all")
    public List<Category> getAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.removeCategory(id);
    }
}
