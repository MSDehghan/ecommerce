package com.bestretail.ecommerce.user.entites;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class User {
    @Id
    private int id;

    @NotEmpty
    @Column(unique = true)
    private String email;

    public User(@NotEmpty String email) {
        this.email = email;
    }

    // Used by hibernate
    public User() {
    }

    @Basic
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
