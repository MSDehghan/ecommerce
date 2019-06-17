package com.bestretail.ecommerce.security;

import com.bestretail.ecommerce.app.user.UserEntity;
import com.bestretail.ecommerce.app.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public DomainUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        return new UserAuth(userEntity);
    }
}
