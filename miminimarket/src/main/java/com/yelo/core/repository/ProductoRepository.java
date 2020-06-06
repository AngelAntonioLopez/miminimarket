package com.yelo.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yelo.core.Entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	
	Optional<Producto> findByName(String name);
	boolean existsByName(String name);
	
	Optional<Producto> findByCode(String code);
	boolean existsByCode(String code);
	

}
