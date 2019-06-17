package com.bestretail.ecommerce.app.user;

import com.bestretail.ecommerce.app.user.dto.LoginDto;
import com.bestretail.ecommerce.app.user.dto.UserDTO;
import com.bestretail.ecommerce.security.SecurityUtils;
import com.bestretail.ecommerce.security.jwt.JWTFilter;
import com.bestretail.ecommerce.security.jwt.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final UserService service;


    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    public UserController(UserService service, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.service = service;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<TokenWrapper> authorize(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = (loginDto.isRememberMe() == null) ? false : loginDto.isRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new TokenWrapper(jwt), httpHeaders, HttpStatus.OK);
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAnonymous()")
    public void register(@Valid @RequestBody UserDTO user) {
        service.registerUser(user);
    }

    @GetMapping("/userinfo")
    @PreAuthorize("hasRole('USER')")
    public UserEntity getInfo() {
        return service.getUserInfo(SecurityUtils.getCurrentUserLogin().orElseThrow(IllegalStateException::new));
    }

    public static class TokenWrapper {
        private String token;

        public TokenWrapper(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }
}
