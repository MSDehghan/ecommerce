package com.bestretail.ecommerce.config;

import com.bestretail.ecommerce.app.user.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public DomainUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.bestretail.ecommerce.app.user.User user = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Email not found"));

        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString()))
        );
    }
}
