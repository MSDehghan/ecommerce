package com.bestretail.ecommerce.menu;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
public class MenuOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 254)
    @Column(nullable = false, length = 254)

    private String name;
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)

    private Set<MenuOption> subMenus;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuOption parent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuOption that = (MenuOption) o;
        return id != null && ((MenuOption) o).getId() != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MenuOption> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(Set<MenuOption> subMenus) {
        this.subMenus = subMenus;
    }

    public MenuOption getParent() {
        return parent;
    }

    public void setParent(MenuOption parent) {
        this.parent = parent;
    }
}
