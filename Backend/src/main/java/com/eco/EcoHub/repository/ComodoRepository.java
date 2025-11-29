package com.eco.EcoHub.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eco.EcoHub.entity.Comodo;

@Repository
public interface ComodoRepository extends JpaRepository<Comodo, UUID> {
	Optional<Comodo> findByIdComodo(UUID idComodo);
	
	

}
