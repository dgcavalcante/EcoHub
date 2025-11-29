package com.eco.EcoHub.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eco.EcoHub.comodoDTO.ComodoDTO;
import com.eco.EcoHub.entity.Comodo;
import com.eco.EcoHub.service.ComodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comodo")
public class ComodoController {
	@Autowired
	private ComodoService comodoService;
	
	@PostMapping("/cadastro")
	public ResponseEntity<?> cadastrarComodo(@Valid @RequestBody ComodoDTO comodoDTO) {
		try {
			Comodo novoComodo = comodoService.criarComodo(comodoDTO);
			return ResponseEntity.ok(novoComodo);
			} catch (RuntimeException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/adicionarDispositivo/{idComodo}/{idDispositivo}")
	public ResponseEntity<?> adicionarDispositivoAoComodo(@PathVariable UUID idComodo, @PathVariable UUID idDispositivo) {
		try {
			comodoService.adicionarDispositivoAoComodo(idComodo, idDispositivo);
			return ResponseEntity.ok("Dispositivo adicionado ao comodo com sucesso.");
			} catch (RuntimeException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<?> listarPerfilPorId(@PathVariable UUID id) {
		try {
			Comodo comodo = comodoService.listarComodoPorId(id);
			return ResponseEntity.ok(comodo);
			} catch (RuntimeException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<?> atualizarPerfil(@PathVariable UUID id, @Valid @RequestBody ComodoDTO comodoDTO) {
		try {
			Comodo comodoAtualizado = comodoService.atualizarComodo(id, comodoDTO);
			return ResponseEntity.ok(comodoAtualizado);
			} catch (RuntimeException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?> deletarComodo(@PathVariable UUID id) {
		try {
			comodoService.deletar(id);
			return ResponseEntity.ok("Comodo deletado com sucesso.");
			} catch (RuntimeException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
