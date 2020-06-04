package com.yelo.core.DTO;

import java.util.Calendar;

import com.sun.istack.NotNull;


public class UsuarioDto {
	@NotNull
	private String name;
	@NotNull
	private String password;
	@NotNull
	private String email;
	@NotNull
	private String cellphone;
	@NotNull
	private Calendar birthday;
	@NotNull
	private String type;

	public UsuarioDto(String name, String password, String email, String cellphone, Calendar birthday, String type) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.cellphone = cellphone;
		this.birthday = birthday;
		this.type = type;
	}

	public UsuarioDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
