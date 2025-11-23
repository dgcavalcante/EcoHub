package com.eco.EcoHub.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eco.EcoHub.DispositivoDTO.DispositivoDTO;
import com.eco.EcoHub.entity.Dispositivo;
import com.eco.EcoHub.repository.DispositivoRepository;

@Service
public class DispositivoService {
	@Autowired
	private DispositivoRepository DispositivoRepository;
	
	public Dispositivo criarDispositivo(DispositivoDTO dispositivoDTO) {
		Dispositivo novoDispositivo = new Dispositivo();
        novoDispositivo.setNomeDispositivo(dispositivoDTO.getNome());
        novoDispositivo.setTipoDispositivo(dispositivoDTO.getTipo());
        novoDispositivo.setConsumoEnergetico(dispositivoDTO.getConsumoPorHora());
        novoDispositivo.setStatusDispositivo(dispositivoDTO.isStatusDispositivo());
        return DispositivoRepository.save(novoDispositivo);
	}
	
	public Dispositivo listarDispositivoPorId(UUID id) {
	    return DispositivoRepository.findByIdDispositivo(id)
	            .orElseThrow(() -> new RuntimeException("Dispositivo não encontrado com o ID: " + id));
	}
	
	public void deletarDispositivo(UUID id) {
	    Dispositivo dispositivo = DispositivoRepository.findByIdDispositivo(id)
	            .orElseThrow(() -> new RuntimeException("Dispositivo não encontrado com o ID: " + id));
	    DispositivoRepository.delete(dispositivo);
	}
	
	public Iterable<Dispositivo> listarTodosDispositivos() {
	    return DispositivoRepository.findAll();
	}


}
