package com.bestretail.ecommerce.app.user;

import com.bestretail.ecommerce.app.user.dto.UserDTO;
import com.bestretail.ecommerce.exceptions.ResourceAlreadyExistsException;
import com.bestretail.ecommerce.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserEntity registerUser(UserDTO userDTO) {
        Optional<UserEntity> optionalUser = repository.findByEmail(userDTO.getEmail());

        if (optionalUser.isPresent())
            throw new ResourceAlreadyExistsException("User with this email already exists.");

        UserEntity userEntity = userDTO.toEntity();

        return repository.save(userEntity);
    }

    public UserEntity getUserInfo(String email) {
        Optional<UserEntity> optionalUser = repository.findByEmail(email);

        if (!optionalUser.isPresent())
            throw new ResourceNotFoundException("User not found!");

        return optionalUser.get();
    }
}
