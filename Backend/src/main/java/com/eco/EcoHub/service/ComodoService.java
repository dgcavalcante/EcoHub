package com.eco.EcoHub.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eco.EcoHub.comodoDTO.ComodoDTO;
import com.eco.EcoHub.entity.Comodo;
import com.eco.EcoHub.entity.Dispositivo;
import com.eco.EcoHub.repository.ComodoRepository;
import com.eco.EcoHub.repository.DispositivoRepository;

import jakarta.validation.Valid;

@Service
public class ComodoService {
	@Autowired
	private ComodoRepository comodoRepository;
	
	@Autowired
	private DispositivoRepository dispositivoRepository;
	
	public Comodo criarComodo(ComodoDTO comodoDTO) {
		Comodo novoComodo = new Comodo();
		novoComodo.setNomeComodo(comodoDTO.getName());
		return comodoRepository.save(novoComodo);
	}

	public Comodo listarComodoPorId(UUID id) {
		Comodo comodo = comodoRepository.findByIdComodo(id)
				.orElseThrow(() -> new RuntimeException("Comodo nao encontrado com o ID: " + id));
		return comodo;
	}

	public Comodo atualizarComodo(UUID id, @Valid ComodoDTO comodoDTO) {
		Comodo comodoExistente = comodoRepository.findByIdComodo(id)
				.orElseThrow(() -> new RuntimeException("Comodo nao encontrado com o ID: " + id));
		comodoExistente.setNomeComodo(comodoDTO.getName());
		return comodoRepository.save(comodoExistente);
	}

	public void deletar(UUID id) {
		Comodo comodoExistente = comodoRepository.findByIdComodo(id)
				.orElseThrow(() -> new RuntimeException("Comodo nao encontrado com o ID: " + id));
		comodoRepository.delete(comodoExistente);		
	}
	public void adicionarDispositivoAoComodo(UUID idComodo, UUID idDispositivo) {
		Comodo comodo = comodoRepository.findByIdComodo(idComodo)
				.orElseThrow(() -> new RuntimeException("Comodo nao encontrado com o ID: " + idComodo));
		Dispositivo dispositivo = dispositivoRepository.findByIdDispositivo(idDispositivo)
				.orElseThrow(() -> new RuntimeException("Dispositivo nao encontrado com o ID: " + idDispositivo));
		comodo.getDispositivos().add(dispositivo);
		dispositivo.dispositivo().add(comodo);
		comodoRepository.save(comodo);
		dispositivoRepository.save(dispositivo);
	}
	

}
