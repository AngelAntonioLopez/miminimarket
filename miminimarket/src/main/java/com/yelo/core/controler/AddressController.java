package com.yelo.core.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yelo.core.DTO.AddressDto;
import com.yelo.core.DTO.Mensaje;
import com.yelo.core.Entity.Address;
import com.yelo.core.Entity.Usuario;
import com.yelo.core.service.AddressService;
import com.yelo.core.service.UsuarioService;

@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "*")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Address>> list(){
		List<Address> list = addressService.list();
		return new ResponseEntity<List<Address>>(list, HttpStatus.OK);		
	}
	
	@GetMapping("/pagaddress")
	public ResponseEntity<Page<Address>> paginaAddress(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "name") String order,
			@RequestParam(defaultValue = "true") boolean asc
			){
		Page<Address> pagAddress = addressService.paginas(
				PageRequest.of(page, size, Sort.by(order)));
		
		if(!asc) {
			pagAddress = addressService.paginas(
					PageRequest.of(page, size, Sort.by(order).ascending()));
		}
		
		return new ResponseEntity<Page<Address>>(pagAddress, HttpStatus.OK);
	}
	
	@GetMapping("/direccion/{id}")
	public ResponseEntity<Address> getById(@PathVariable("id") int id){
		if(addressService.existById(id)){
			Address address = addressService.getOne(id).get();
			return new ResponseEntity<Address>(address , HttpStatus.OK);
		}else {
			return new ResponseEntity(new Mensaje("Dicha direccion no ha sido encontrada") , HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<List<Address>> getByUsuario(@PathVariable("id")int id){
		if(!usuarioService.existById(id)){
			return new ResponseEntity(new Mensaje("no existe ese usuario"), HttpStatus.NOT_FOUND);
		}
		
		Usuario usuario = usuarioService.getOne(id).get();
		
		List<Address> list = usuario.getAddresses();
		
		if(list.isEmpty() || list == null) {
			return new ResponseEntity(new Mensaje("no existen direcciones para este usuario"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Address>>(list , HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody AddressDto addressDto){
		boolean flag = usuarioService.existById(addressDto.getUsuario());
		if(!flag) {
			return new ResponseEntity(new Mensaje("no existen direcciones para este usuario"), HttpStatus.NOT_FOUND);
		}
		Usuario user = usuarioService.getOne(addressDto.getUsuario()).get();
		Address address = new Address(addressDto.getCalle(), addressDto.getCasa(), addressDto.getLugar(), addressDto.getUbicacion(), user);
		addressService.save(address);
		return new ResponseEntity(new Mensaje("Direccion Creada con exito"), HttpStatus.OK);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<?> update (@PathVariable("id") int id, @RequestBody AddressDto address){
		if(addressService.existById(id)) {
			Address updatedAddress = addressService.getOne(id).get();
			
			updatedAddress.setCalle(address.getCalle());
			updatedAddress.setCasa(address.getCasa());
			updatedAddress.setLugar(address.getLugar());
			updatedAddress.setUbicacion(address.getUbicacion());
			
			addressService.save(updatedAddress);
			
			return new ResponseEntity(new Mensaje("Dirección Actualizida"), HttpStatus.OK);
		}
		return new ResponseEntity(new Mensaje("Error: No existe direccion con ese ID"), HttpStatus.NOT_FOUND);
	}	
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id){
		if(!addressService.existById(id)) {
			return new ResponseEntity(new Mensaje("no se encontró ninguna dirección con esta ID"), HttpStatus.NOT_FOUND);
		}
		addressService.delete(id);
		return new ResponseEntity(new Mensaje("la direccion ha sido eliminada exitosamente"), HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
