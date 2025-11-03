package com.eco.EcoHub.controller;

import com.eco.EcoHub.entity.User;
import com.eco.EcoHub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // libera o acesso do front Angular
public class UserController {

    @Autowired
    private UserService userService;

    // ENDPOINT: Cadastro
    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody User user) {
        try {
            User novoUser = userService.cadastrar(user);
            return ResponseEntity.ok(novoUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ENDPOINT: Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String senha) {
        try {
            User user = userService.autenticar(email, senha);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
