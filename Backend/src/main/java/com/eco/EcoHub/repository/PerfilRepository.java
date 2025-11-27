package com.eco.EcoHub.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eco.EcoHub.entity.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, UUID> {
	Optional<Perfil> findByIdPerfil(UUID idPerfil);

}
