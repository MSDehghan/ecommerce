package com.bestretail.ecommerce.security;

import com.bestretail.ecommerce.app.user.UserEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class UserAuth extends User {
    private final Integer userId;

    public UserAuth(UserEntity userEntity) {
        super(userEntity.getEmail(),
                userEntity.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(userEntity.getRole().toString()))
        );
        userId = userEntity.getId();
    }

    public Integer getUserId() {
        return userId;
    }
}
