package com.yelo.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yelo.core.Entity.Address;
import com.yelo.core.Entity.Usuario;

@Repository
public interface AddressRespository extends JpaRepository<Address, Integer>{
	
}
