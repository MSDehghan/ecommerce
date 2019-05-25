package com.bestretail.ecommerce.app.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuOption, Integer> {
    Optional<MenuOption> findByName(String name);

    @Query("SELECT m FROM MenuOption m JOIN FETCH m.subMenus WHERE m.parent IS NULL")
    List<MenuOption> findAllParents();
}
