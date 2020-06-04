package com.yelo.core.controler;

import com.yelo.core.DTO.Mensaje;
import com.yelo.core.DTO.UsuarioDto;
import com.yelo.core.Entity.Usuario;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yelo.core.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Usuario>> list(){
		List<Usuario> list = usuarioService.list();
		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable("id") int id){
		if(!usuarioService.existById(id)) {
			return new  ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND); 
		}else {
			Usuario usuario = usuarioService.getOne(id).get(); 
			return new ResponseEntity<Usuario>(usuario , HttpStatus.OK);
		}
	}
	
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<Usuario> getByNombre (@PathVariable("nombre") String nombre){
		if(!usuarioService.existByNombre(nombre)) {
			return new ResponseEntity(new Mensaje("No se a encontrado ningun usuario"), HttpStatus.NOT_FOUND);
		}else {
			Usuario usuario = usuarioService.getByNombre(nombre).get();
			return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody UsuarioDto usuarioDto){
		if(StringUtils.isBlank(usuarioDto.getName()) || StringUtils.isBlank(usuarioDto.getCellphone()) 
				|| StringUtils.isBlank(usuarioDto.getPassword()) || StringUtils.isBlank(usuarioDto.getBirthday().toString())  ) {
			return new ResponseEntity(new Mensaje("Todos los campos son obligatorio"), HttpStatus.BAD_REQUEST);
		}
		if(usuarioService.existByEmail(usuarioDto.getEmail())) {
			return new ResponseEntity(new Mensaje("el email colocado ya esta en uso en el sistema"), HttpStatus.BAD_REQUEST);
		}
		Usuario usuario = new Usuario(usuarioDto.getName(), usuarioDto.getPassword(), usuarioDto.getEmail(), usuarioDto.getCellphone(), usuarioDto.getBirthday(), usuarioDto.getType());
		usuarioService.save(usuario);
		
		return new ResponseEntity(new Mensaje("Usuario creado con exito"), HttpStatus.OK);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update (@PathVariable("id")int id,  @RequestBody UsuarioDto usuarioDto){
		if(!usuarioService.existById(id)) {
			return new ResponseEntity(new Mensaje("el usuario a modificar no existe"), HttpStatus.NOT_FOUND);
		}
		if(usuarioService.existByEmail(usuarioDto.getEmail()) && usuarioService.getByEmail(usuarioDto.getEmail()).get().getId() != id) {
			return new ResponseEntity(new Mensaje("el email colocado ya esta en uso en el sistema"), HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(usuarioDto.getName()) || StringUtils.isBlank(usuarioDto.getCellphone()) 
				|| StringUtils.isBlank(usuarioDto.getPassword()) || StringUtils.isBlank(usuarioDto.getBirthday().toString())  ) {
			return new ResponseEntity(new Mensaje("Todos los campos son obligatorio"), HttpStatus.BAD_REQUEST);
		}

		
		Usuario usuario = usuarioService.getOne(id).get();
		
		usuario.setName(usuarioDto.getName());
		usuario.setPassword(usuarioDto.getPassword());
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setCellphone(usuario.getCellphone());
		usuario.setBirthday(usuarioDto.getBirthday());
		usuario.setType(usuarioDto.getType());
		
		usuarioService.save(usuario);
		
		return new ResponseEntity(new Mensaje("Usuario Actualizado con exito"), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete (@PathVariable("id")int id){
		if(!usuarioService.existById(id)) {
			return new ResponseEntity(new Mensaje("el usuario no existe"), HttpStatus.NOT_FOUND);
		}
		
		usuarioService.delete(id);
		
		return new ResponseEntity(new Mensaje("el usuario se ha eliminado"), HttpStatus.OK);
		
	}
	
}
