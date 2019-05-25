package com.bestretail.ecommerce.app.menu;

import com.bestretail.ecommerce.app.category.Category;
import com.bestretail.ecommerce.exceptions.ResourceAlreadyExistsException;
import com.bestretail.ecommerce.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    private final MenuRepository repository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    public MenuOption addMenu(MenuOption menu){
        if (menu.getParent() != null && menu.getParent().getId() == null)
            throw new IllegalArgumentException("Parent id must be present.");

        Optional<MenuOption> currentMenu = repository.findByName(menu.getName());
        if (currentMenu.isPresent())
            throw new ResourceAlreadyExistsException("Menu already exists");

        return repository.save(menu);
    }

    public List<MenuOption> findAllParents(){
        return repository.findAllParents();
    }

    public MenuOption findMenuById(int id){
        Optional<MenuOption> menu = repository.findById(id);
        return menu.orElseThrow(IllegalArgumentException::new);
    }

    public void removeMenuOption(int id) {
        Optional<MenuOption> menuOptionOptional = repository.findById(id);
        if (menuOptionOptional.isPresent()) {
            repository.delete(menuOptionOptional.get());
        } else {
            throw new ResourceNotFoundException("Menu not found!");
        }
    }
}
