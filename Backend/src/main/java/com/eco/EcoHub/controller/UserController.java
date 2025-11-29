package com.eco.EcoHub.controller;

import com.eco.EcoHub.emailDTO.EmailDTO;
import com.eco.EcoHub.entity.User;
import com.eco.EcoHub.senhaDTO.Senha;
import com.eco.EcoHub.service.EmailService;
import com.eco.EcoHub.service.UserService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*") // libera o acesso do front Angular
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
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

    // ENDPOINT: login / /login?email=teste@gmail.com&senha=1234
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String senha) {
        try {
            userService.autenticar(email, senha);
            return ResponseEntity.ok("Login realizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // ENDPOINT: Atualizar usuário /atualizar?cpf=123
    @PutMapping("/atualizar") // atualizar/colocar o cpf
    public ResponseEntity<?> atualizarUsuario(@RequestParam String cpf, @Valid @RequestBody User userAtualizado) {
        try { 
            User usuarioExistente = userService.listarUsuarioPorCpf(cpf);
            if (usuarioExistente == null) {
                return ResponseEntity.badRequest().body("Usuário não encontrado!");
            }
            usuarioExistente.setNomeCompleto(userAtualizado.getNomeCompleto());
            usuarioExistente.setDataNascimento(userAtualizado.getDataNascimento());
            usuarioExistente.setEmail(userAtualizado.getEmail());
            userService.salvar(usuarioExistente);
            return ResponseEntity.ok("Usuário atualizado com sucesso!");
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
                return ResponseEntity.ok("Email de recuperação de senha enviado com sucesso, verifique sua caixa de entrada e spam!");
            } else {
                return ResponseEntity.badRequest().body("E-mail não cadastrado!");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // ENDPOINT: Formulário de atualizar senha
    @PutMapping("/atualizar/{email}")
    public ResponseEntity<?> atualizarSenha(@PathVariable String email, @Valid @RequestBody Senha senha, BindingResult result) {
    	if(result.hasErrors()) {
    		List<String> errors = result.getAllErrors()
					.stream()
					.map(error -> error.getDefaultMessage())
					.toList();
    		return ResponseEntity.badRequest().body(errors);
    	}
    			try {
			boolean senhasCoincidem = userService.verificarSenha(senha.getSenha(), senha.getConfirmarSenha());
			if(!senhasCoincidem) {
				return ResponseEntity.badRequest().body("As senhas não coincidem!");
			}
			User user = userService.listarUsuarioPorEmail(email);
			if(user == null) {
				return ResponseEntity.badRequest().body("Usuário não encontrado!");
			}
			user.setSenha(passwordEncoder.encode(senha.getSenha()));
			userService.salvar(user);
			return ResponseEntity.ok("Senha atualizada com sucesso!");
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
    // ENDPOINT: deletar usuário
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable(value="id") UUID id) {
		try {
			User usuarioExistente = userService.encontrarUsuarioPorID(id);
			if (usuarioExistente == null) {
				return ResponseEntity.badRequest().body("Usuário não encontrado!");
			}
			userService.deletarUsuario(usuarioExistente);
			return ResponseEntity.ok("Usuário deletado com sucesso!");
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}