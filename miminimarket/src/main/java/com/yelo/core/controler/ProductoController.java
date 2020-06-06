package com.yelo.core.controler;

import java.util.List;
import java.util.function.DoubleUnaryOperator;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RestController;

import com.yelo.core.DTO.Mensaje;
import com.yelo.core.DTO.ProductoDto;
import com.yelo.core.Entity.Producto;
import com.yelo.core.service.ProductoService;
import com.yelo.core.service.UsuarioService;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
	
	@Autowired
	ProductoService productoService;
	
	@GetMapping("/list")
	public ResponseEntity<List<Producto>>list(){
		List<Producto> lista = productoService.list();
		return new ResponseEntity<List<Producto>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/pag")
	public ResponseEntity<Page<Producto>> pagProducto(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "name") String order,
			@RequestParam(defaultValue = "true") boolean asc
			){
		Page<Producto> pgProducto = productoService.Paginas(
				PageRequest.of(page, size, Sort.by(order)));
		
		if(!asc) {
			 pgProducto = productoService.Paginas(
						PageRequest.of(page, size, Sort.by(order).ascending()));
		}
		return new ResponseEntity<Page<Producto>>(pgProducto, HttpStatus.OK);
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<Producto> getById(@PathVariable("id") int id){
		if(!productoService.existById(id)){
			return new ResponseEntity(new Mensaje("No existe ese producto"), HttpStatus.NOT_FOUND);
		}else {
			Producto producto = productoService.getOne(id).get();
			return new ResponseEntity<Producto>(producto, HttpStatus.OK);
		}
	}
	
	@GetMapping("/name/{nombre}")
	public ResponseEntity<Producto> getByNombre(@PathVariable("nombre") String nombre){
		if(!productoService.existByName(nombre)) {
			return new ResponseEntity(new Mensaje("No eciste ese producto"), HttpStatus.NOT_FOUND);
		}else {
			Producto producto = productoService.getByName(nombre).get();
			return new  ResponseEntity<Producto>(producto, HttpStatus.OK);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody ProductoDto productoDto){
		if(StringUtils.isBlank(productoDto.getName())
		|| StringUtils.isBlank(productoDto.getDescription())
		|| StringUtils.isBlank(productoDto.getImg())
		|| productoDto.getPrice() < 0 )
		{
			return new ResponseEntity(new Mensaje("Todos los campos son obligatorios"), HttpStatus.BAD_REQUEST);
		}
		if(productoService.existByName(productoDto.getName())) {
			return new ResponseEntity(new Mensaje("Ya tenemos un producto con ese nombre"), HttpStatus.BAD_REQUEST);
		}
		if(productoService.existByCode(productoDto.getCode())) {
			return new ResponseEntity(new Mensaje("Ya tenemos un producto con ese Codigo"), HttpStatus.BAD_REQUEST);
		}
		
		Producto producto = new Producto(productoDto.getName(), productoDto.getDescription(), productoDto.getImg(), productoDto.getStatus(), productoDto.getCode(), productoDto.getPrice());
		
		
		productoService.save(producto);
		return new ResponseEntity(new Mensaje("Producto Creado con exito"), HttpStatus.OK);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update (@PathVariable("id")int id, @RequestBody ProductoDto productoDto){
		if(!productoService.existById(id)) {
			return new ResponseEntity(new Mensaje("El Producto que quiere actualizar no existe"), HttpStatus.NOT_FOUND);
		}
		if(productoService.existByName(productoDto.getName()) && productoService.getByName(productoDto.getName()).get().getId() != id) {
			return new ResponseEntity(new Mensaje("El nombre del producto que quiere actualizar ya existe"), HttpStatus.BAD_REQUEST);
		}
		if(productoService.existByCode(productoDto.getCode()) && productoService.getByCode(productoDto.getCode()).get().getId() != id) {
			return new ResponseEntity(new Mensaje("El ID del producto que quiere actualizar ya existe"), HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(productoDto.getName()) || StringUtils.isBlank(productoDto.getDescription()) ||
		   StringUtils.isBlank(productoDto.getStatus()) || productoDto.getPrice() < 0){
			return new ResponseEntity(new Mensaje("todos los campos son obligatorios"), HttpStatus.BAD_REQUEST);
		}
			Producto producto = productoService.getOne(id).get();
			producto.setName(productoDto.getName());
			producto.setDescription(productoDto.getDescription());
			producto.setImg(productoDto.getName());
			producto.setStatus(productoDto.getName());
			producto.setCode(productoDto.getCode());
			producto.setPrice(productoDto.getPrice());
			productoService.save(producto);
			return new ResponseEntity(new Mensaje("Producto Actualizado con exito"), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete (@PathVariable("id") int id){
		if(!productoService.existById(id)) {
			return new ResponseEntity(new Mensaje("El producto con ese id no existe"), HttpStatus.NOT_FOUND);
		}
		
		productoService.delete(id);
		
		return new ResponseEntity<>(new Mensaje("El producto ha sido eliminado"),HttpStatus.OK); 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
