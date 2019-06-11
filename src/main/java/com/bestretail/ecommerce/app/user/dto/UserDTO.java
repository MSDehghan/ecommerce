package com.bestretail.ecommerce.app.user.dto;

import com.bestretail.ecommerce.app.user.Role;
import com.bestretail.ecommerce.app.user.Sex;
import com.bestretail.ecommerce.app.user.User;
import com.bestretail.ecommerce.config.Security;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDTO {
    @Email
    @Size(min = 5, max = 254)
    private String email;

    @Size(max = 254)
    private String name;

    @Size(max = 254)
    private String lastName;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @NotBlank
//    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
//    @Size(min = 8, max = 255)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Role role;

    private Sex sex;

    public User toEntity() {
        User user = new User();
        user.setEmail(email);
        user.setLastName(lastName);
        user.setName(name);
        user.setSex(sex);
        user.setRole(role == null ? Role.ROLE_USER : Role.ROLE_ADMIN);
        user.setPassword(Security.PASSWORD_ENCODER.encode(password));
        return user;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
