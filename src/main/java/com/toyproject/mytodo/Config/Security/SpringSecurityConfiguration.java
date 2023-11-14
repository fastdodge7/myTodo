package com.toyproject.mytodo.Config.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
        UserDetails userDetails1 = createUser("정민규", "test");
        UserDetails userDetails2 = createUser("fastdodge7", "test");
        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    @Bean
    public PasswordEncoder customPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private UserDetails createUser(String name, String pw) {
        UserDetails userDetails = User.builder()
                .passwordEncoder(input -> customPasswordEncoder().encode(input))
                .username(name)
                .password(pw)
                .roles("USER", "ADMIN")
                .build();
        return userDetails;
    }
}
