package com.noliter.myground.login;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) {
        // DB에서 사용자 조회 예시 (임시 하드코딩)
        return User.withUsername(username)
                .password("{noop}password123")
                .roles("USER")
                .build();
    }
}
