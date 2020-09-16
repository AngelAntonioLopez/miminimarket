package com.yelo.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yelo.core.Entity.Address;
import com.yelo.core.Entity.Usuario;
import com.yelo.core.repository.AddressRespository;
import com.yelo.core.repository.UsuarioRepository;

@Service
@Transactional
public class AddressService {

	@Autowired
	AddressRespository addressRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	public List<Address> list(){
		return addressRepository.findAll();
	}
	
	public Page<Address> paginas(Pageable pageable){
		return addressRepository.findAll(pageable);
	}
	
	public Optional<Address> getOne(int id){
		return addressRepository.findById(id);
	}
	
	public void save(Address address){
		addressRepository.save(address);
	}
	
	public void delete(int id){
		addressRepository.deleteById(id);
	}
	
	public boolean existById(int id){
		return addressRepository.existsById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
