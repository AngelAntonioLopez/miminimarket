package com.yelo.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yelo.core.Entity.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{
	Optional<Usuario> findByName(String name);
	boolean existsByName(String nombre);
	
	Optional<Usuario> findByEmail(String email);
	boolean existsByEmail(String email);
}
