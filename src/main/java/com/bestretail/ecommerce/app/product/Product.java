package com.bestretail.ecommerce.app.product;

import com.bestretail.ecommerce.app.category.Category;
import com.bestretail.ecommerce.app.maker.Maker;
import com.bestretail.ecommerce.app.promo.Promo;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @NotBlank
    @Size(max = 254)
    @Column(nullable = false, length = 254)
    private String name;

    @Basic(optional = false)
    @DecimalMin("0")
    private Integer mainPrice;

    @Column(columnDefinition = "text")
    private String description;

    @Basic(optional = false)
    @DecimalMin("0")
    private Integer remaining;

    @ElementCollection
    private Set<String> images;

    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne(optional = false)
    private Maker maker;

    @ManyToOne
    private Promo promotion;

    public Promo getPromotion() {
        return promotion;
    }

    public void setPromotion(Promo promotion) {
        this.promotion = promotion;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMainPrice() {
        return mainPrice;
    }

    public void setMainPrice(Integer mainPrice) {
        this.mainPrice = mainPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Maker getMaker() {
        return maker;
    }

    public void setMaker(Maker maker) {
        this.maker = maker;
    }
}
