package com.eco.EcoHub.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eco.EcoHub.DispositivoDTO.DispositivoDTO;
import com.eco.EcoHub.entity.Dispositivo;
import com.eco.EcoHub.service.DispositivoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/dispositivo")
@CrossOrigin(origins = "*") // libera o acesso do front Angular
public class DispositivoController {
	@Autowired
	private DispositivoService dispositivoService;
	
	@PostMapping("/cadastro")
	public ResponseEntity<?> cadastrarDispositivo(@Valid @RequestBody DispositivoDTO dispositivoDTO) {
	    try {
	        Dispositivo novoDispositivo = dispositivoService.criarDispositivo(dispositivoDTO);
	        return ResponseEntity.ok(novoDispositivo);
	    } catch (RuntimeException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	@GetMapping("/listar/{id}")
	public ResponseEntity<?> listarDispositivoPorId(@PathVariable UUID id) {
	    try {
	        Dispositivo dispositivo = dispositivoService.listarDispositivoPorId(id);
	        return ResponseEntity.ok(dispositivo);
	    } catch (RuntimeException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	@GetMapping("/listar/todos")
	public ResponseEntity<?> listarTodosDispositivos() {
	    try {
	        Iterable<Dispositivo> dispositivos = dispositivoService.listarTodosDispositivos();
	        return ResponseEntity.ok(dispositivos);
	    } catch (RuntimeException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?> deletarDispositivo(@PathVariable UUID id) {
	    try {
	        dispositivoService.deletarDispositivo(id);
	        return ResponseEntity.ok("Dispositivo deletado com sucesso!");
	    } catch (RuntimeException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	

}
