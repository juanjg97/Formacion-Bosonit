package com.bosonit.block15security.application.services;

import com.bosonit.block15security.domain.entities.User;
import com.bosonit.block15security.repositories.UserRepository;
import com.bosonit.block15security.security.UserDetailsImpl;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service @RequiredArgsConstructor @Transactional
@Slf4j
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository
                .findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email "+ email + "no existe"));

        return new UserDetailsImpl(user);
    }
}
