package com.eco.EcoHub.controller;

import com.eco.EcoHub.emailDTO.EmailDTO;
import com.eco.EcoHub.entity.User;
import com.eco.EcoHub.service.EmailService;
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
    @Autowired
    private EmailService emailService;
    
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
  
    // ENDPOINT: Recuperar senha
    @PostMapping("/recuperar-senha")
    public ResponseEntity<?> recuperarSenha(@RequestBody @Valid EmailDTO emailDTO) {
    	try {
    		boolean exist = userService.validarEmail(emailDTO.getEmail());
    		if(exist) {
    			emailService.sendEmail(emailDTO.getEmail(), "Recuperação de senha", "Olá, você solicitou a recuperação de senha. Clique no link para redefinir sua senha:");
            	return ResponseEntity.ok("Email enviado com sucesso!");
    		} else {
    			return ResponseEntity.badRequest().body("E-mail não cadastrado!");
    		}
    	} catch (RuntimeException e) {
    		return ResponseEntity.badRequest().body(e.getMessage());
    	}
    }
}
