package com.eco.EcoHub.controller;

import com.eco.EcoHub.emailDTO.EmailDTO;
import com.eco.EcoHub.entity.User;
import com.eco.EcoHub.service.EmailService;
import com.eco.EcoHub.service.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
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
    
    // ENDPOINT: Atualizar usuário
    @PutMapping("/atualizar/{cpf}") 
    public ResponseEntity<?> atualizarUsuario(@PathVariable String cpf, @Valid @RequestBody User userAtualizado) {
        try { 
            User usuarioExistente = userService.listarUsuarioPorCpf(cpf);
            if (usuarioExistente == null) {
                return ResponseEntity.badRequest().body("Usuário não encontrado!");
            }
            // Copia as propriedades do request para a entidade existente, preservando campos imutáveis
            // Preserva id e cpf (caso existam esses campos na entidade)
            BeanUtils.copyProperties(userAtualizado, usuarioExistente, "id", "cpf");

            User salvo = userService.salvar(usuarioExistente);
            return ResponseEntity.ok(salvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // ENDPOINT: Listar todos os usuários
    @GetMapping("/listarTodos")
    public ResponseEntity<?> listarUsuarios() {
                try {
            return ResponseEntity.ok(userService.listarUsuarios());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // ENDPOINT: Listar usuário por CPF
    @GetMapping("/listar/{cpf}")
    public ResponseEntity<?> listarUsuarioPorCpf(@PathVariable String cpf) {
                try {
                    return ResponseEntity.ok(userService.listarUsuarioPorCpf(cpf));
                } catch (RuntimeException e) {
                    return ResponseEntity.badRequest().body(e.getMessage());}
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