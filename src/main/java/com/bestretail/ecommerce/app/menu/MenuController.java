package com.bestretail.ecommerce.app.menu;

import com.bestretail.ecommerce.app.category.Category;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/menu")
public class MenuController {
    private final MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    @PostMapping
    public MenuOption addCategory(@Valid @RequestBody MenuOption menuOption) {
        return service.addMenu(menuOption);
    }

    @GetMapping("/{id}")
    public MenuOption getCategory(@PathVariable int id) {
        return service.findMenuById(id);
    }

    @GetMapping("/all")
    public List<MenuOption> getAll() {
        return service.findAllParents();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.removeMenuOption(id);
    }
}
