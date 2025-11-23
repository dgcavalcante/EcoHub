package com.eco.EcoHub.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eco.EcoHub.entity.Dispositivo;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, UUID> {
	Optional<Dispositivo> findByIdDispositivo(UUID idDispositivo);
	boolean existsByemUmComodoTrue(UUID idDispositivo);

}
