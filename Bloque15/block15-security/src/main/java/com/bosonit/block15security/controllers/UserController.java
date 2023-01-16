package com.bosonit.block15security.controllers;

import com.bosonit.block15security.domain.entities.User;
import com.bosonit.block15security.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public List<User> listUsuarios() {
        return userRepository.findAll();
    }
}