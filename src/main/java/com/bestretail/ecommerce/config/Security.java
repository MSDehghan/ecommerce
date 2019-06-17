package com.bestretail.ecommerce.config;

import com.bestretail.ecommerce.security.jwt.JWTConfigurer;
import com.bestretail.ecommerce.security.jwt.TokenProvider;
import com.bestretail.ecommerce.security.DomainUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {
    public static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private final TokenProvider tokenProvider;
    private final DomainUserDetailsService userDetailsService;

    public Security(DomainUserDetailsService userDetailsService, TokenProvider tokenProvider) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return PASSWORD_ENCODER;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/v1/user/login").permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().configurationSource(corsConfiguration())
                .and()
                .csrf().disable()
                .apply(new JWTConfigurer(tokenProvider));
    }

    private CorsConfigurationSource corsConfiguration() {
        return request -> {
            CorsConfiguration conf = new CorsConfiguration();
            conf.applyPermitDefaultValues();

            conf.addAllowedMethod(HttpMethod.OPTIONS);
            conf.addAllowedMethod(HttpMethod.GET);
            conf.addAllowedMethod(HttpMethod.PUT);
            conf.addAllowedMethod(HttpMethod.DELETE);

            conf.addAllowedMethod(HttpMethod.POST);
            conf.addAllowedMethod(HttpMethod.OPTIONS);
            conf.addAllowedMethod(HttpMethod.GET);
            conf.addAllowedMethod(HttpMethod.PUT);
            conf.addAllowedMethod(HttpMethod.DELETE);
            conf.addAllowedHeader("Authorization");
            conf.addExposedHeader("Authorization");
            conf.setAllowCredentials(true);
            return conf;
        };
    }
}
