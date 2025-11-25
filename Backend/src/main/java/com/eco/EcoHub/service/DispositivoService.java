package com.eco.EcoHub.service;

import java.math.BigDecimal;
import java.util.Map;
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
	public void injetarDadosConsumo(UUID id, int mes, int quantidadeHoras) {
		Dispositivo dispositivo = DispositivoRepository.findByIdDispositivo(id)
	            .orElseThrow(() -> new RuntimeException("Dispositivo não encontrado com o ID: " + id));
		dispositivo.injetarConsumoPorHora(mes, quantidadeHoras);
		DispositivoRepository.save(dispositivo);
		
	}
	public Map<String, BigDecimal> pegarRelatorioTotal(UUID id) {
		Dispositivo dispositivo = DispositivoRepository.findByIdDispositivo(id)
	            .orElseThrow(() -> new RuntimeException("Dispositivo não encontrado com o ID: " + id));
		Map<String, BigDecimal> relatorio = dispositivo.relatorio();
		return relatorio;
	}


}
