package com.bestretail.ecommerce.app.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void register(@Valid @RequestBody UserDTO user) {
        service.registerUser(user);
    }

    @PostMapping("/login")
    public void login(@RequestParam String username, @RequestParam String password) {
    }

    @GetMapping("/userinfo")
    public User getInfo() {
        return service.getUserInfo(1);
    }
}
