package com.example.springsecuritydemo1.service;

import com.example.springsecuritydemo1.model.User;
import com.example.springsecuritydemo1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                    .map(str -> new SimpleGrantedAuthority(str))
                    .collect(Collectors.toList());
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
//                    .roles()
                    .authorities(authorities)
                    .build();
            System.out.println(userDetails.getAuthorities());
            return userDetails;
        } else {
            throw new UsernameNotFoundException(email);
        }
    }
}
