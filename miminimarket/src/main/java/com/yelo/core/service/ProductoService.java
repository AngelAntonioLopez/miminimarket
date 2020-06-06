package com.yelo.core.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yelo.core.repository.ProductoRepository;
import com.yelo.core.Entity.Producto;

@Service
@Transactional
public class ProductoService {
	
	@Autowired
	ProductoRepository productoRepository;
	
	public List<Producto> list(){
		return productoRepository.findAll();
	}
	
	public Page<Producto> Paginas(Pageable pageable){
		return productoRepository.findAll(pageable);
	}
	
	public Optional<Producto> getOne(int id){
		return productoRepository.findById(id);
	}
	
	public Optional<Producto> getByName(String name){
		return productoRepository.findByName(name);
	}
	
	public void save(Producto producto) {
		while(!existByCode(producto.getCode())) {
			producto.setCode(generateCode());
			System.out.println("code cambiado");
		}
		productoRepository.save(producto);
	}
	
	
	
	
	public void delete(int id) {
		productoRepository.deleteById(id);;
	}
	
	public boolean existById(int id) {
		return productoRepository.existsById(id);
	}
	
	public boolean existByName(String name) {
		return productoRepository.existsByName(name);
	}
	
	public boolean existByCode(String code) {
		return productoRepository.existsByCode(code);
	}
	
	public String generateCode() {
		Random aleatorio = new Random();
        String alfa = "ABCDEFGHIJKLMNOPQRSTVWXYZ";
        String cadena = ""; 
        int numero, forma;
        String alfac ="";
        
        for(int i = 1; i<4; i++){
            forma=(int)(aleatorio.nextDouble() * alfa.length()-1+0);
            alfac += alfa.charAt(forma); 
        }
        numero=(int)(aleatorio.nextDouble() * 99+100);
        cadena=alfac+numero;
        
        return cadena;
	}

	public Optional<Producto> getByCode(String code) {
		return productoRepository.findByCode(code);
	}
}
