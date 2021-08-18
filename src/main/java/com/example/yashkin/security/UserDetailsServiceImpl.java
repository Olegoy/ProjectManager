package com.example.yashkin.security;

import com.example.yashkin.entity.UserEntity;
import com.example.yashkin.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.getUserByLogin(login).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist"));
        return SecurityUser.fromUser(userEntity);
    }
}
