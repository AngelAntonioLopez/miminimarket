package com.yelo.core.DTO;

import javax.persistence.Column;

import com.sun.istack.NotNull;

public class ProductoDto {
	
	@NotNull
	private String name;
	@NotNull
	private String description;
	@NotNull
	private String img;
	@NotNull
	private String status;
	@NotNull
	private String code;
	@NotNull
	private double price;
	
	public ProductoDto(String name, String description, String img, String status, String code, double price) {
		this.name = name;
		this.description = description;
		this.img = img;
		this.status = status;
		this.code = code;
		this.price = price;
	}

	public ProductoDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
	
	
	
}
