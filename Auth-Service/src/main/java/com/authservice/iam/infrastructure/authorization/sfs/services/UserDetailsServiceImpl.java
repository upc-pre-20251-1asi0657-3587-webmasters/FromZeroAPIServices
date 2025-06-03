package com.authservice.iam.infrastructure.authorization.sfs.services;

import com.authservice.iam.domain.exceptions.UserNotFoundException;
import com.authservice.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.authservice.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service(value = "defaultUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UserNotFoundException {
        var user = userRepository.findByUserEmail(userEmail).orElseThrow(() -> new UserNotFoundException(userEmail));
        return UserDetailsImpl.build(user);
    }
}
