package com.yelo.core.DTO;

public class AddressDto {
	
	public String calle;
	public String casa;
	public String lugar;
	public String ubicacion;
	public int usuario;
	
	

	public AddressDto(String calle, String casa, String lugar, String ubicacion, int usuario) {
		super();
		this.calle = calle;
		this.casa = casa;
		this.lugar = lugar;
		this.ubicacion = ubicacion;
		this.usuario = usuario;
	}
	
	public AddressDto() {
		super();
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCasa() {
		return casa;
	}

	public void setCasa(String casa) {
		this.casa = casa;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	
	
	

}
