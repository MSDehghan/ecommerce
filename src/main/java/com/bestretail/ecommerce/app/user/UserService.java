package com.bestretail.ecommerce.app.user;

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

    public User registerUser(UserDTO userDTO) {
        Optional<User> optionalUser = repository.findByEmail(userDTO.getEmail());

        if (optionalUser.isPresent())
            throw new ResourceAlreadyExistsException("User with this email already exists.");

        User user = userDTO.toEntity();

        return repository.save(user);
    }

    public User getUserInfo(int id) {
        Optional<User> optionalUser = repository.findById(id);

        if (!optionalUser.isPresent())
            throw new ResourceNotFoundException("User not found!");

        return optionalUser.get();
    }
}
