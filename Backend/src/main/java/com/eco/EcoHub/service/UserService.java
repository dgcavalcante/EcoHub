package com.eco.EcoHub.service;

import com.eco.EcoHub.entity.User;
import com.eco.EcoHub.repository.UserRepository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User cadastrar(User user) {

        // 1. Verifica se email já está cadastrado
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado!");
        }

        // 2. Verifica se CPF já está cadastrado
        if (userRepository.existsByCpf(user.getCpf())) {
            throw new RuntimeException("CPF já cadastrado!");
        }

        // 3. Verifica se as senhas coincidem
        if (!user.getSenha().equals(user.getConfirmarSenha())) {
            throw new RuntimeException("As senhas não coincidem!");
        }

        // 4. Criptografa a senha antes de salvar
        user.setSenha(passwordEncoder.encode(user.getSenha()));

        // Remove o confirmarSenha antes de salvar (boa prática)
        user.setConfirmarSenha(null);

        return userRepository.save(user);
    }
    
    public User salvar(User user) {
		return userRepository.save(user);
	}
    
    public User encontrarUsuarioPorID(UUID id) {
    	return userRepository.findByIdUser(id)
    			.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }
    
    public User listarUsuarioPorCpf(String cpf) {
		return userRepository.findByCpf(cpf)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
	}
    
    public User listarUsuarioPorEmail(String email) {
    	return userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }

    public User autenticar(String email, String senha) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (!passwordEncoder.matches(senha, user.getSenha())) {
            throw new RuntimeException("Senha incorreta!");
        }
        return user;
    }
    
    public Iterable<User> listarUsuarios() {
		return userRepository.findAll();
	}
    
    public boolean validarEmail(String email) {
    	return userRepository.existsByEmail(email);
    }
    
    public boolean verificarSenha(String senha, String confirmarSenha) {
    	if (!senha.equals(confirmarSenha)) {
			return false;
		} else {
			return true;
		}
    }

	public void deletarUsuario(User usuarioExistente) {
		userRepository.delete(usuarioExistente);
	}
}
