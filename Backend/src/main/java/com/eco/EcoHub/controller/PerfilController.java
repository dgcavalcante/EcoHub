package com.eco.EcoHub.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eco.EcoHub.entity.Perfil;
import com.eco.EcoHub.perfilDTO.PerfilDTO;
import com.eco.EcoHub.service.PerfilService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	@Autowired
	private PerfilService perfilService;
	
	@PostMapping("/cadastro")
	public ResponseEntity<?> cadastrarPerfil(@Valid @RequestBody PerfilDTO perfilDTO) {
		try {
			Perfil novoPerfil = perfilService.criarPerfil(perfilDTO);
			return ResponseEntity.ok(novoPerfil);
			} catch (RuntimeException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<?> atualizarPerfil(@PathVariable UUID id, @Valid @RequestBody PerfilDTO perfilDTO) {
		try {
			Perfil perfilAtualizado = perfilService.atualizarPerfil(id, perfilDTO);
			return ResponseEntity.ok(perfilAtualizado);
			} catch (RuntimeException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?> deletarPerfil(@PathVariable UUID id) {
		try {
			perfilService.deletarPerfil(id);
			return ResponseEntity.ok("Perfil deletado com sucesso.");
			} catch (RuntimeException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<?> listarPerfilPorId(@PathVariable UUID id) {
		try {
			Perfil perfil = perfilService.listarPerfilPorId(id);
			return ResponseEntity.ok(perfil);
			} catch (RuntimeException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/listarTodos")
	public ResponseEntity<?> listarTodosPerfis() {
		try {
			Iterable<Perfil> perfis = perfilService.listarTodosPerfis();
			return ResponseEntity.ok(perfis);
			} catch (RuntimeException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
