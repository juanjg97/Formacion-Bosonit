package com.bosonit.block10dockerizeapp;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @GetMapping("/saludar")
    public ResponseEntity<?> getPersonaById() {
            return ResponseEntity.ok().body("Hello World!");
    }

}
