package com.yelo.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yelo.core.Entity.Usuario;
import com.yelo.core.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public List<Usuario> list(){
		return usuarioRepository.findAll();
	}
	
	public Optional<Usuario> getOne(int id){
		return usuarioRepository.findById(id);
	}
	
	public Optional<Usuario> getByNombre(String nombre){
		return usuarioRepository.findByName(nombre);
	}
	
	public Optional<Usuario> getByEmail(String email){
		return usuarioRepository.findByEmail(email);
	}
	
	
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public void delete(int id) {
		usuarioRepository.deleteById(id);
	}
	
	public boolean existById(int id) {
		return usuarioRepository.existsById(id);
	}
	 
	public boolean existByNombre(String nombre) {
		return usuarioRepository.existsByName(nombre);
	}
	
	public boolean existByEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}
}
